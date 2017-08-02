package org.btkj.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Banner;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.mongo.BonusScaleMapper;
import org.btkj.user.mybatis.EntityGenerator;
import org.btkj.user.mybatis.Tx;
import org.btkj.user.pojo.info.AppInfo;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.TenantPagingMasterInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.model.BonusScale;
import org.btkj.user.pojo.model.BonusScale.State;
import org.btkj.user.pojo.param.EmployeeEditParam;
import org.btkj.user.pojo.param.TenantSetParam;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.user.pojo.submit.UserSearcher;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.BannerMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {
	
	@Resource
	private Tx tx;
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private BannerMapper bannerMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private ConfigService configService;
	@Resource
	private EmployeeMapper employeeMapper;
	@Resource
	private BonusScaleMapper bonusScaleMapper;
	
	@Override
	public Result<Pager<UserPagingInfo>> userPaging(UserSearcher searcher) {
		return Result.result(userMapper.paging(searcher));
	}

	@Override
	public Result<Pager<TenantPagingInfo>> tenantPaging(TenantSearcher searcher) {
		Pager<TenantPagingInfo> pager = tenantMapper.paging(searcher);
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		for (TenantPagingInfo info : pager.getList()) {
			set1.add(info.getRegionId());
			if (info instanceof TenantPagingMasterInfo)
				set2.add(((TenantPagingMasterInfo) info).getAppId());
		}
		Map<Integer, Region> regions = configService.regions(set1);
		Map<Integer, AppPO> apps = searcher.getClient() == Client.BAO_TU_MANAGER ? appMapper.getByKeys(set2) : null;
		for (TenantPagingInfo info : pager.getList()) {
			Region region = regions.remove(info.getRegionId());
			info.setRegionName(null == region ? null : region.getName());
			if (info instanceof TenantPagingMasterInfo) {
				AppPO app = apps.remove(((TenantPagingMasterInfo) info).getAppId());
				((TenantPagingMasterInfo) info).setAppName(null == app ? null : app.getName());
			}
		}
		return Result.result(tenantMapper.paging(searcher));
	}
	
	@Override
	public Result<Pager<EmployeePagingInfo>> employeePaging(EmployeeSearcher searcher) {
		Pager<EmployeePagingInfo> pager = null;
		if (null != searcher.getMobile()) {
			UserPO user = userMapper.getUserByMobile(searcher.getAppId(), searcher.getMobile());
			if (null == user)
				return Result.result(Pager.EMPLTY);
			searcher.setUid(user.getUid());
			pager = employeeMapper.paging(searcher);
		} else 
			pager = employeeMapper.paging(searcher);
		
		Set<Integer> set = new HashSet<Integer>();
		for (EmployeePagingInfo info : pager.getList()) 
			set.add(info.getParentId());
		List<EmployeePO> parents = new ArrayList<EmployeePO>(employeeMapper.getByKeys(new ArrayList<Integer>(set)).values());
		for (EmployeePagingInfo info : pager.getList()) {
			for (EmployeePO employee : parents) {
				if (employee.getId() == info.getParentId())
					info.setParentUid(employee.getUid());
			}
		}
		set.clear();
		for (EmployeePagingInfo info : pager.getList()) {
			set.add(info.getUid());
			set.add(info.getParentUid());
		}
		List<UserPO> users = new ArrayList<UserPO>(userMapper.getByKeys(new ArrayList<Integer>(set)).values());
		for (EmployeePagingInfo info : pager.getList()) {
			for (UserPO user : users) {
				if (info.getUid() == user.getUid()) {
					info.setName(user.getName());
					info.setMobile(user.getMobile());
				}
				if (info.getParentUid() == user.getUid()) {
					info.setParentName(user.getName());
					info.setParentMobile(user.getMobile());
				}
			}
		}
		return Result.result(employeeMapper.paging(searcher));
	}
	
	@Override
	public Result<Pager<ApplyPagingInfo>> applyPaging(int tid, int page, int pageSize) {
		Pager<ApplyPagingInfo> pager = applyMapper.paging(tid, page, pageSize);
		Set<Integer> set = new HashSet<Integer>();
		for (ApplyPagingInfo info : pager.getList()) {
			set.add(info.getUid());
			set.add(info.getParentUid());
		}
		
		List<UserPO> users = new ArrayList<UserPO>(userMapper.getByKeys(new ArrayList<Integer>(set)).values());
		for (ApplyPagingInfo info : pager.getList()) {
			for (UserPO user : users) {
				if (user.getUid() == info.getUid()) {
					info.setName(user.getName());
					info.setMobile(user.getMobile());
					info.setIdentity(user.getIdentity());
					info.setIdentityFace(user.getIdentityFace());
					info.setIdentityBack(user.getIdentityBack());
				}
				if (user.getUid() == info.getParentUid()) {
					info.setParentName(user.getName());
					info.setParentMobile(user.getMobile());
				}
			}
		}
		return Result.result(pager);
	}
	
	@Override
	public Result<Void> employeeEdit(int tid, int employeeId, EmployeeEditParam param) {
		EmployeePO employee = employeeMapper.getByKey(param.getTargetId());
		if (null == employee)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (employee.getTid() != tid)
			return Consts.RESULT.FORBID;
		employee.setMod(param.getMod());
		employee.setCommercialRate(param.getCMRate());
		employee.setCompulsoryRate(param.getCPRate());
		employee.setUpdated(DateUtil.currentTime());
		employeeMapper.update(employee);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> bannerAdd(int appId, int tid, int idx, String icon, String link) {
		Banner banner = EntityGenerator.newBanner(appId, tid, idx, icon, link);
		try {
			bannerMapper.insert(banner);
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.FAILURE;
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> bannerEdit(int id, String icon, String link) {
		Banner banner = bannerMapper.getByKey(id);
		if (null == banner)
			return BtkjConsts.RESULT.BANNER_NOT_EXIST;
		banner.setImage(icon);
		banner.setLink(link);
		banner.setUpdated(DateUtil.currentTime());
		bannerMapper.update(banner);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> bannerDelete(int id) {
		bannerMapper.delete(id);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> tenantSet(TenantPO tenant, TenantSetParam param) {
		if (null != param.getNonAutoBind()) {
			if (param.getNonAutoBind().isEmpty())
				tenant.setNonAutoBind(null);
			else {
				StringBuilder builder = new StringBuilder();
				for (long cid : param.getNonAutoBind())
					builder.append(cid).append(Consts.SYMBOL_UNDERLINE);
				builder.deleteCharAt(builder.length() - 1);
				tenant.setNonAutoBind(builder.toString());
			}
		}
		if (null != param.getMod())
			tenant.setMod(param.getMod());
		if (null != param.getTeamDepth()) 
			tenant.setTeamDepth(Math.min(GlobalConfigContainer.getGlobalConfig().getTeamDepth(), param.getTeamDepth()));
		if (null != param.getServicePhone())
			tenant.setServicePhone(param.getServicePhone());
		tenant.setUpdated(DateUtil.currentTime());
		tenantMapper.update(tenant);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> tenantSet(User user, int tid, String contacts, String contactsMobile, String tname, String license, String licenseImage, int expire) {
		TenantPO tenant = tenantMapper.getByKey(tid);
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		if (tenant.getAppId() != user.getAppId())
			return Consts.RESULT.FORBID;
		if (StringUtil.hasText(contacts))
			tenant.setContacts(contacts);
		if (StringUtil.hasText(contactsMobile))
			tenant.setContactsMobile(contactsMobile);
		if (StringUtil.hasText(tname))
			tenant.setName(tname);
		if (StringUtil.hasText(license))
			tenant.setLicense(license);
		if (StringUtil.hasText(licenseImage))
			tenant.setLicenseImage(licenseImage);
		if (0 != expire)
			tenant.setExpire(expire);
		tenant.setUpdated(DateUtil.currentTime());
		tenantMapper.update(tenant);
		return Consts.RESULT.OK;
	}
	
	@Override
	public List<AppInfo> apps() {
		Map<Integer, AppPO> apps = appMapper.getAll();
		List<AppInfo> list = new ArrayList<AppInfo>();
		Set<Integer> set = new HashSet<Integer>();
		for (AppPO po : apps.values()) {
			list.add(new AppInfo(po));
			set.add(po.getRegion());
		}
		Map<Integer, Region> regions = configService.regions(set);
		for (AppInfo info : list) {
			Region region = regions.get(info.getRegion());
			info.setRegionName(null == region ? null : region.getName());
		}
		return list;
	}
	
	@Override
	public Result<Integer> appAdd(int region, String name, int maxTenantsCount, int maxArticlesCount) {
		if (null == configService.region(region))
			return BtkjConsts.RESULT.REGION_NOT_EXIST;
		AppPO app = EntityGenerator.newApp(region, name, maxTenantsCount, maxArticlesCount);
		appMapper.insert(app);
		Result<Integer> result = Result.result(Code.OK);
		result.setAttach(app.getId());
		return result;
	}
	
	@Override
	public Result<Void> appUpdate(int appId, int region, String name, int maxTenantsCount, int maxArticlesCount) {
		AppPO app = appMapper.getByKey(appId);
		if (null == app)
			return BtkjConsts.RESULT.APP_NOT_EXIST;
		if (0 != region) {
			if (null == configService.region(region))
				return BtkjConsts.RESULT.REGION_NOT_EXIST;
			app.setRegion(region);
		}
		if (null != name)
			app.setName(name);
		app.setMaxTenantsCount(Math.max(0, maxTenantsCount));
		app.setMaxArticlesCount(Math.max(0, maxArticlesCount));
		app.setUpdated(DateUtil.currentTime());
		appMapper.update(app);
		return Consts.RESULT.OK;
	}
	
	@Override
	public void calculateTeamExploits(int time, TenantPO tenant, Map<Integer, BonusScale> personalExploits) {
		Map<String, BonusScale> exploits = tx.calculateTeamExploits(time, tenant, personalExploits);
		bonusScaleMapper.insert(exploits);
		tenant.setScaleRewardTime(time);
		tenant.setUpdated(DateUtil.currentTime());
		tenantMapper.update(tenant);
	}
	
	@Override
	public BonusScale bonusScale(String key) {
		return bonusScaleMapper.getByKey(key);
	}
	
	@Override
	public Pager<BonusScale> bonusScales(int tid, EmployeeParam param) {
		return bonusScaleMapper.paging(tid, param);
	}
	
	@Override
	public Result<BonusScale> bonusScaleAudit(int tid, String key, boolean agree) {
		BonusScale bonusScale = bonusScaleMapper.getByKey(key);
		if (null == bonusScale)
			return BtkjConsts.RESULT.BONUS_SCALE_NOT_EXIST;
		if (bonusScale.getTid() != tid || bonusScale.getState() != State.AUDIT)
			return Consts.RESULT.FORBID;
		bonusScale.setState(agree ? State.AGREE : State.REJECT);
		bonusScale.setUpdated(DateUtil.currentTime());
		bonusScaleMapper.update(bonusScale);
		return Result.result(bonusScale);
	}
}
