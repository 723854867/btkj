package org.btkj.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.TenantListInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.user.mybatis.EntityGenerator;
import org.btkj.user.mybatis.Tx;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
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
	public Tenant getTenantById(int tid) {
		return tenantMapper.getByKey(tid);
	}

	@Override
	public Result<?> apply(User user, EmployeeForm chief) {
		if (chief.getApp().getId() != user.getAppId())
			return Result.result(Code.FORBID);
		return _doApply(chief.getTenant(), user, chief);
	}

	private Result<?> _doApply(Tenant tenant, User user, EmployeeForm chief) {
		ApplyInfo ai = applyMapper.getByTidAndUid(tenant.getTid(), user.getUid());
		if (null != ai)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (employeeMapper.isEmployee(tenant.getTid(), user.getUid()))
			return Result.result(BtkjCode.ALREADY_IS_EMPLOYEE);
		applyMapper.insert(EntityGenerator.newApply(tenant, user, chief));
		return Result.success();
	}

	@Override
	public Result<Void> applyProcess(int tid, int uid, boolean agree) {
		ApplyInfo info = applyMapper.getAndDel(tid, uid);
		if (null == info)
			return Result.result(BtkjCode.APPLY_EXIST);
		if (!agree) // 拒绝申请直接返回即可
			return Result.success();

		Employee chief = employeeMapper.getByKey(info.getChief());
		Tenant tenant = tenantMapper.getByKey(info.getTid());
		Employee employee = EntityGenerator.newEmployee(userMapper.getByKey(info.getUid()), tenant, chief);
		tx.insertEmployee(employee).finish();
		return Result.success();
	}

	@Override
	public Result<?> tenantAdd(App app, int region, String tname, User user, String licenseFace, String licenseBack, String servicePhone) {
		String lockId = userMapper.lockUser(user.getUid());
		if (null == lockId)
			return Consts.RESULT.USER_STATUS_CHANGED;
		try {
			if (userService.tenantNumMax(user))
				return BtkjConsts.RESULT.USER_TENANT_NUM_MAXIMUM;
			tx.tenantAdd(app, region, tname, user, licenseFace, licenseBack, servicePhone).finish();
		} finally {
			userMapper.releaseUserLock(user.getUid(), lockId);
		}
		return Result.success();
	}

	@Override
	public TenantListInfo tenantListInfo(Client client, App app, User user) {
		List<Employee> employees = employeeMapper.ownedTenants(user);
		List<Integer> tids = new ArrayList<Integer>(employees.size());
		for (Employee employee : employees)
			tids.add(employee.getTid());
		List<Tenant> own = new ArrayList<Tenant>(tenantMapper.getByKeys(tids).values());
		List<Tenant> audit = new ArrayList<Tenant>(tenantMapper.getByKeys(applyMapper.applyListTids(user)).values());
		return new TenantListInfo(own, employees, audit);
	}
}
