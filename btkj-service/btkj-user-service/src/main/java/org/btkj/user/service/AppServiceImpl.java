package org.btkj.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Community;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.NonAutoInsurance;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.AppCreateInfo;
import org.btkj.pojo.info.AppMainPageInfo;
import org.btkj.pojo.info.PCMainPageInfo;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
import org.btkj.pojo.info.tips.BannerTips;
import org.btkj.pojo.info.tips.MainTenantTips;
import org.btkj.pojo.info.tips.NonAutoInsuranceTips;
import org.btkj.pojo.model.AppCreateModel;
import org.btkj.pojo.model.UserModel;
import org.btkj.user.api.AppService;
import org.btkj.user.persistence.Tx;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.BannerMapper;
import org.btkj.user.redis.CommunityMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.NonAutoInsuranceMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("appService")
public class AppServiceImpl implements AppService {
	
	@Resource
	private Tx tx;
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
	private CommunityMapper communityMapper;
	@Resource
	private NonAutoInsuranceMapper nonAutoInsuranceMapper;
	
	@Override
	public App getAppById(int appId) {
		return appMapper.getByKey(appId);
	}
	
	@Override
	public Result<IMainPageInfo> mainPage(int appId) {
		App app = appMapper.getByKey(appId);
		if (null == app)
			return Result.result(BtkjCode.APP_NOT_EXIST);
		return _appMainPageInfo(null, appId, null);
	}
	
	@Override
	public Result<IMainPageInfo> mainPage(Client client, String token, int tid) {
		UserModel userModel = userMapper.getUserByToken(client, token);
		if (null == userModel)
			return Result.result(Code.TOKEN_INVALID);
		User user = userModel.getUser();
		switch (client) {
		case PC:
			return Result.result(new PCMainPageInfo(user));
		default:
			Tenant tenant = 0 == tid ? null : tenantMapper.getByKey(tid);
			if (0 != tid && null == tenant)
				return Result.result(BtkjCode.TENANT_NOT_EXIST);
			if (0 != tid) {
				Employee employee = employeeMapper.getByTidAndUid(tid, user.getUid());
				if (null == employee)
					return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
			}
			return _appMainPageInfo(user, user.getAppId(), tenant);
		}
	}
	
	/**
	 * App 首页信息
	 * 
	 * @param app
	 * @param tid
	 * @return
	 */
	private Result<IMainPageInfo> _appMainPageInfo(User user, int appId, Tenant tenant) {
		AppMainPageInfo pageInfo = new AppMainPageInfo(user);
		
		// 设置 main tenant 信息
		MainTenantTips mainTenant = new MainTenantTips(tenant);
		List<BannerTips> banners = new ArrayList<BannerTips>();				// banner 条
		for (Banner banner : bannerMapper.getByAppIdAndTid(appId, null == tenant ? 0 : tenant.getTid())) 
			banners.add(new BannerTips(banner));
		mainTenant.setBannerList(banners);
		List<NonAutoInsuranceTips> insurances = new ArrayList<NonAutoInsuranceTips>();		// 非车险列表
		for (NonAutoInsurance insurance : nonAutoInsuranceMapper.getByAppIdAndTid(appId, null == tenant ? 0 : tenant.getTid()))
			insurances.add(new NonAutoInsuranceTips(insurance));
		mainTenant.setNonAutoInsuranceList(insurances);
		Community community = communityMapper.getByKey(appId);
		if (null != community)
			mainTenant.setCommunityBackground(community.getImage());
		pageInfo.setMainTenant(mainTenant);
		return Result.result(pageInfo);
	}
	
	@Override
	public Result<AppCreateInfo> addApp(int region, String appName, int maxTenantsCount, int tenantRegion,
			String tenantName, String pwd, String mobile, String name, String identity) {
		AppCreateModel acm = tx.addApp(region, appName, maxTenantsCount, tenantRegion, tenantName, pwd, mobile, name, identity);
		AppCreateInfo aci = new AppCreateInfo();
		aci.setAppId(acm.getApp().getId());
		aci.setTid(acm.getTenant().getTid());
		aci.setUid(acm.getUser().getUid());
		return Result.result(aci);
	}
}
