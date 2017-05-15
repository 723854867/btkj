package org.btkj.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.AppState;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.AppListInfo;
import org.btkj.pojo.info.MainPageInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.AppSearcher;
import org.btkj.user.BeanGenerator;
import org.btkj.user.api.AppService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.BannerMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
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
	public Result<Void> appState(int id, AppState state) {
		App app = appMapper.getByKey(id);
		if(null == app)
			return Result.result(BtkjCode.APP_NOT_EXIST);
		if (app.getState() != state.mark()) {
			app.setState(state.mark());
			app.setUpdated(DateUtils.currentTime());
			appMapper.update(app);
		}
		return Result.success();
	}
	
	@Override
	public Result<Pager<AppListInfo>> appList(AppSearcher searcher) {
		return appMapper.appList(searcher);
	}
	
	@Override
	public App getAppById(int appId) {
		return appMapper.getByKey(appId);
	}
	
	@Override
	public Result<MainPageInfo> mainPage(Client client, User user, EmployeeForm em) {
		return _mainPageInfo(user, em);
	}
	
	/**
	 * App 首页信息
	 * 
	 * @param app
	 * @param tid
	 * @return
	 */
	private Result<MainPageInfo> _mainPageInfo(User user, EmployeeForm em) {
		Tenant tenant = null == em ? null : em.getTenant();
		int appId = user.getAppId();
		if (null == tenant) {
			int mainTid = userMapper.mainTenant(user.getUid());
			if (0 == mainTid) {
				List<Employee> employees = employeeMapper.ownedTenants(user);
				if (null != employees && !employees.isEmpty()) 
					mainTid = employees.get(0).getTid();
			}
			if (0 != mainTid)
				tenant = tenantMapper.getByKey(mainTid);
		}
		List<Banner> banners = bannerMapper.getByAppIdAndTid(appId, null == tenant ? 0 : tenant.getTid());
		return Result.result(new MainPageInfo(user, tenant, banners));
	}
	
	@Override
	public App addApp(int region, String name, int maxTenantsCount, boolean tenantAddAutonomy) {
		App app = BeanGenerator.newApp(region, name, maxTenantsCount, tenantAddAutonomy);
		appMapper.insert(app);
		return app;
	}
	
	@Override
	public int tenantNum(App app) {
		return tenantMapper.countByAppId(app.getId());
	}
}
