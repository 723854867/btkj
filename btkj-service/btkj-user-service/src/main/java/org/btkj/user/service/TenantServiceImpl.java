package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.user.BeanGenerator;
import org.btkj.user.api.TenantService;
import org.btkj.user.persistence.Tx;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("tenantService")
public class TenantServiceImpl implements TenantService {
	
	@Resource
	private Tx tx;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public Tenant getTenantById(int tid) {
		return tenantMapper.getByKey(tid);
	}
	
	@Override
	public Result<?> apply(User user, int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		User chief = userMapper.getByKey(employee.getUid());
		if (chief.getAppId() != user.getAppId())
			return Result.result(Code.FORBID);
		return _doApply(tenantMapper.getByKey(employee.getTid()), user, chief);
	}
	
	private Result<?> _doApply(Tenant tenant, User user, User chief) {
		ApplyInfo ai = applyMapper.getByTidAndUid(tenant.getTid(), user.getUid());
		if (null != ai)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (null != employeeMapper.getByTidAndUid(tenant.getTid(), user.getUid()))
			return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		applyMapper.insert(BeanGenerator.newApply(tenant.getTid(), user.getUid(), chief.getUid()));
		return Result.success();
	}
	
	@Override
	public Result<Pager<ApplyInfo>> applyList(int tid, int page, int pageSize) {
		return applyMapper.applyList(tid, page, pageSize);
	}
	
	@Override
	public Result<Void> applyProcess(int tid, int uid, boolean agree) {
		ApplyInfo info = applyMapper.getAndDel(tid, uid);
		if (null == info)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (!agree)				// 拒绝申请直接返回即可
			return Result.success();
		
		Employee chief = employeeMapper.getByTidAndUid(tid, info.getChief());
		Tenant tenant = tenantMapper.getByKey(info.getTid());
		Employee employee = BeanGenerator.newEmployee(userMapper.getByKey(info.getUid()), tenant, chief);
		tx.tenantJoin(employee);
		return Result.success();
	}
	
	@Override
	public Result<Void> tenantAdd(App app, Region region, String tenantName, String pwd, int uid) {
		User user = userMapper.getByKey(uid);
		if (null == user)
			return Result.result(Code.USER_NOT_EXIST);
		if (user.getAppId() != app.getId())
			return Result.result(Code.FORBID);
		String lockId = userMapper.lockUser(uid);
		if (null == lockId)
			return Result.result(Code.USER_STATUS_CHANGED);
		try {
			// 判断是否超过最大代理公司数
			int tenantNum = employeeMapper.tenantNum(uid);
			if (tenantNum >= GlobalConfigContainer.getGlobalConfig().getMaxTenantNum())
				return Result.result(BtkjCode.TENANT_COUNT_MAXIMUM);
			tx.tenantAdd(app, region, tenantName, pwd, user);
			return Result.success();
		} finally {
			userMapper.releaseUserLock(uid, lockId);
		}
	}
	
	@Override
	public Result<Void> tenantAdd(App app, Region region, String tenantName, String name, String mobile, String identity, String pwd) {
		return Result.success();
	}
}
