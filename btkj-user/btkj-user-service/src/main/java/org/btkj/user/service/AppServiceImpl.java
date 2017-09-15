package org.btkj.user.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.App.Mod;
import org.btkj.pojo.entity.user.Banner;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.info.MainPageInfo;
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
	public App app(int appId) {
		return appMapper.getByKey(appId);
	}
	
	@Override
	public Map<Integer, App> apps(Collection<Integer> apps) {
		return appMapper.getByKeys(apps);
	}
	
	@Override
	public Result<MainPageInfo> mainPage(User user, EmployeeTip employee) {
		return _mainPageInfo(user, employee);
	}
	
	/**
	 * App 首页信息
	 * 
	 * @param app
	 * @param tid
	 * @return
	 */
	private Result<MainPageInfo> _mainPageInfo(User user, EmployeeTip employee) {
		int appId = user.getAppId();
		Tenant tenant = null;
		if (null == employee) {
			int mainTid = userMapper.mainTenant(user.getUid());
			if (0 == mainTid) {
				Map<Integer, Employee> employees = employeeMapper.ownedTenants(user.getUid());
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
	public int tenantNum(App app) {
		return tenantMapper.countByAppId(app.getId());
	}
	
	@Override
	public Result<Void> seal(int appId) {
		App app = appMapper.getByKey(appId);
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
		App app = appMapper.getByKey(appId);
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
