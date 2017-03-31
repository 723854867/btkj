package org.btkj.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.Region;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.TenantService;
import org.btkj.user.persistence.Tx;
import org.btkj.user.redis.hook.ApplyHook;
import org.btkj.user.redis.mapper.AppMapper;
import org.btkj.user.redis.mapper.BannerMapper;
import org.btkj.user.redis.mapper.EmployeeMapper;
import org.btkj.user.redis.mapper.TenantMapper;
import org.btkj.user.redis.mapper.UserMapper;
import org.rapid.util.common.Assert;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("tenantService")
public class TenantServiceImpl implements TenantService {
	
	@Resource
	private Tx tx;
	@Resource
	private AppMapper appMapper;
	@Resource
	private ApplyHook applyHook;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private BannerMapper bannerMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public Map<Integer, App> getApps() {
		return appMapper.getAll();
	}
	
	@Override
	public Map<Integer, Tenant> getTenants() {
		return tenantMapper.getAll();
	}
	
	@Override
	public Map<Integer, Banner> getBanners() {
		return bannerMapper.getAll();
	}
	
	@Override
	public Result<Pager<ApplyInfo>> applyList(int tid, int page, int pageSize) {
		return applyHook.applyList(tid, page, pageSize);
	}
	
	@Override
	public Result<Void> applyProcess(int tid, String applyKey, boolean agree) {
		ApplyInfo info = applyHook.getAndDel(tid, applyKey);
		if (null == info)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (!agree)				// 拒绝申请直接返回即可
			return Result.success();
		
		Employee chief = 0 == info.getChief() ? 
				employeeMapper.getByTidAndLevel(tid, BtkjConsts.EMPLOYEE_ROOT_LEVEL).get(0) : 
					employeeMapper.getByUidAndTid(info.getChief(), tid);
		Tenant tenant = tenantMapper.getByKey(info.getTid());
		Assert.notNull(tenant);
		if (0 == info.getUid())		// 独立 app 的申请
			tx.tenantJoin(info.getName(), info.getIdentity(), applyKey, tenant, chief);
		else 						// 保途 app 的申请
			tx.tenantJoin(userMapper.getByKey(info.getUid()), tenant, chief);
		return Result.success();
	}
	
	@Override
	public Result<Void> tenantAdd(Region region, String appName, String tenantName, String name, String mobile, String identity) {
		if (null == appName) {
			App app = appMapper.getByKey(BtkjConsts.APP_ID_BAOTU);
			tx.tenantAdd(app, region, tenantName, mobile, name, identity);
		} else
			tx.tenantAdd(appName, region, tenantName, mobile, name, identity);
		return Result.success();
	}
}
