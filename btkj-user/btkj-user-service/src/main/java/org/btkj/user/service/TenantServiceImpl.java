package org.btkj.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.vo.ApplyInfo;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.user.mybatis.EntityGenerator;
import org.btkj.user.mybatis.Tx;
import org.btkj.user.pojo.info.TenantListInfo;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.concurrent.ThreadLocalUtil;
import org.springframework.stereotype.Service;

@Service("tenantService")
public class TenantServiceImpl implements TenantService {

	@Resource
	private Tx tx;
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserService userService;
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	@Resource
	private EmployeeService employeeService;

	@Override
	public TenantPO tenant(int tid) {
		return tenantMapper.getByKey(tid);
	}

	@Override
	public Result<?> apply(User user, Employee chief) {
		ApplyInfo ai = applyMapper.getByTidAndUid(chief.getTid(), user.getUid());
		if (null != ai)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (employeeMapper.isEmployee(chief.getTid(), user.getUid()))
			return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		applyMapper.insert(EntityGenerator.newApply(chief.getTenant(), user, chief));
		return Result.success();
	}

	@Override
	public Result<Employee> applyProcess(int tid, int uid, boolean agree) {
		ApplyInfo info = applyMapper.getAndDel(tid, uid);
		if (null == info)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (!agree) // 拒绝申请直接返回即可
			return Result.success();

		EmployeePO chief = employeeMapper.getByKey(info.getChief());
		TenantPO tenant = tenantMapper.getByKey(info.getTid());
		EmployeePO employee = EntityGenerator.newEmployee(info.getUid(), tenant, chief);
		tx.insertEmployee(employee).finish();
		return Result.result(new Employee(null, userMapper.getByKey(uid), tenant, employee));
	}

	@Override
	public Result<Employee> tenantAdd(int appId, int uid, String tname, String license, String licenseImage, String servicePhone, int expire) {
		String lockId = userMapper.lockUser(uid);
		if (null == lockId)
			return Consts.RESULT.USER_STATUS_CHANGED;
		try {
			if (_tenantNumMax(uid))
				return BtkjConsts.RESULT.USER_TENANT_NUM_MAXIMUM;
			tx.tenantAdd(appId, uid, tname, license, licenseImage, servicePhone, expire).finish();
			return Result.result(ThreadLocalUtil.getAndRemove(EntityGenerator.EMPLOYEE_HOLDER));
		} finally {
			userMapper.releaseUserLock(uid, lockId);
		}
	}

	@Override
	public TenantListInfo tenantListInfo(User user) {
		List<EmployeePO> employees = employeeMapper.ownedTenants(user.getUid());
		List<Integer> tids = new ArrayList<Integer>(employees.size());
		for (EmployeePO employee : employees)
			tids.add(employee.getTid());
		List<TenantPO> own = new ArrayList<TenantPO>(tenantMapper.getByKeys(tids).values());
		List<TenantPO> audit = new ArrayList<TenantPO>(tenantMapper.getByKeys(applyMapper.applyTenants(user.getUid())).values());
		return new TenantListInfo(own, employees, audit);
	}
	
	private boolean _tenantNumMax(int uid) {
		int limit = employeeMapper.ownedTenants(uid).size() + applyMapper.applyTenants(uid).size();
		return limit >= GlobalConfigContainer.getGlobalConfig().getMaxTenantNum();
	}
}
