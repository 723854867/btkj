package org.btkj.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.bo.indentity.App;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Banner;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.vo.MainPageInfo;
import org.btkj.user.api.AppService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.mybatis.EntityGenerator;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.BannerMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.message.Result;
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
	public App app(Client client, int appId) {
		AppPO entity = appMapper.getByKey(appId);
		if (null == entity)
			return null;
		return new App(entity, client);
	}
	
	@Override
	public AppPO getAppById(int appId) {
		return appMapper.getByKey(appId);
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
				List<EmployeePO> employees = employeeMapper.ownedTenants(user.getUid());
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
	public AppPO addApp(int region, String name, int maxTenantsCount, int maxArticlesCount) {
		AppPO app = EntityGenerator.newApp(region, name, maxTenantsCount, maxArticlesCount);
		appMapper.insert(app);
		return app;
	}
	
	@Override
	public int tenantNum(AppPO app) {
		return tenantMapper.countByAppId(app.getId());
	}
}
