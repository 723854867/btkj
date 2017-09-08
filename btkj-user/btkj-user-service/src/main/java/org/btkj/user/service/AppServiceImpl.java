package org.btkj.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.AppPO.Mod;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.MainPageInfo;
import org.btkj.pojo.model.identity.App;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.pojo.model.identity.User;
import org.btkj.user.api.AppService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.BannerMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.springframework.stereotype.Service;

@Service("appService")
public class AppServiceImpl implements AppService {
	
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private BannerMapper bannerMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	@Resource
	private EmployeeService employeeService;
	
	@Override
	public AppPO app(int appId) {
		return appMapper.getByKey(appId);
	}
	
	@Override
	public App app(Client client, int appId) {
		AppPO entity = appMapper.getByKey(appId);
		if (null == entity)
			return null;
		return new App(entity, client);
	}
	
	@Override
	public Result<MainPageInfo> mainPage(User user, Employee em) {
		return _mainPageInfo(user, em);
	}
	
	/**
	 * App 首页信息
	 * 
	 * @param app
	 * @param tid
	 * @return
	 */
	private Result<MainPageInfo> _mainPageInfo(User user, Employee employee) {
		TenantPO tenant = null == employee ? null : employee.getTenant();
		int appId = user.getAppId();
		if (null == tenant) {
			int mainTid = userMapper.mainTenant(user.getUid());
			if (0 == mainTid) {
				Map<Integer, EmployeePO> employees = employeeMapper.ownedTenants(user.getUid());
				if (null != employees && !employees.isEmpty()) 
					mainTid = employees.values().iterator().next().getTid();
			}
			if (0 != mainTid)
				tenant = tenantMapper.getByKey(mainTid);
		}
		List<Banner> banners = bannerMapper.getByAppIdAndTid(appId, null == tenant ? 0 : tenant.getTid());
		return Result.result(new MainPageInfo(user, tenant, banners));
	}
	
	@Override
	public int tenantNum(AppPO app) {
		return tenantMapper.countByAppId(app.getId());
	}
	
	@Override
	public Result<Void> seal(int appId) {
		AppPO app = appMapper.getByKey(appId);
		if (null == app)
			return BtkjConsts.RESULT.APP_NOT_EXIST;
		if (!Mod.SEAL.satisfy(app.getMod())) {
			app.setMod(app.getMod() | Mod.SEAL.mark());
			app.setUpdated(DateUtil.currentTime());
			appMapper.update(app);
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> unseal(int appId) {
		AppPO app = appMapper.getByKey(appId);
		if (null == app)
			return BtkjConsts.RESULT.APP_NOT_EXIST;
		if (Mod.SEAL.satisfy(app.getMod())) {
			app.setMod(app.getMod() & (~Mod.SEAL.mark()));
			app.setUpdated(DateUtil.currentTime());
			appMapper.update(app);
		}
		return Consts.RESULT.OK;
	}
}
