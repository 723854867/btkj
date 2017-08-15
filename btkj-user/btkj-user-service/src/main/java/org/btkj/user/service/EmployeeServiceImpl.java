package org.btkj.user.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.pojo.model.EmployeeHolder;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	private Redis redis;
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public EmployeePO employeeById(int employeeId) {
		return employeeMapper.getByKey(employeeId);
	}
	
	@Override
	public Employee employee(int employeeId) {
		EmployeePO employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return null;
		TenantPO tenant = tenantMapper.getByKey(employee.getTid());
		AppPO app = appMapper.getByKey(tenant.getAppId());
		UserPO user = userMapper.getByKey(employee.getUid());
		return new Employee(app, user, tenant, employee);
	}
	
	@Override
	public Map<Integer, EmployeePO> employees(Collection<Integer> ids) {
		return employeeMapper.getByKeys(ids);
	}
	
	@Override
	public EmployeeTip employeeTip(int id) {
		EmployeePO employee = employeeMapper.getByKey(id);
		if (null == employee)
			return null;
		AppPO app = appMapper.getByKey(employee.getAppId());
		UserPO user = userMapper.getByKey(employee.getUid());
		TenantPO tenant = tenantMapper.getByKey(employee.getTid());
		return new EmployeeTip(employee, app, user, tenant);
	}
	
	@Override
	public Map<Integer, EmployeeTip> employeeTips(Collection<Integer> ids) {
		Map<Integer, EmployeePO> employees = employeeMapper.getByKeys(ids);
		Map<Integer, EmployeeTip> tips = new HashMap<Integer, EmployeeTip>();
		if (CollectionUtil.isEmpty(employees))
			return tips;
		Set<Integer> uids = new HashSet<Integer>();
		Set<Integer> tids = new HashSet<Integer>();
		Set<Integer> appIds = new HashSet<Integer>();
		for (EmployeePO po : employees.values()) {
			uids.add(po.getUid());
			tids.add(po.getTid());
			appIds.add(po.getAppId());
		}
		Map<Integer, AppPO> apps = appMapper.getByKeys(appIds);
		Map<Integer, UserPO> users = userMapper.getByKeys(uids);
		Map<Integer, TenantPO> tenants = tenantMapper.getByKeys(tids);
		for (EmployeePO po : employees.values()) 
			tips.put(po.getId(), new EmployeeTip(po, apps.get(po.getAppId()), users.get(po.getUid()), tenants.get(po.getTid())));
		return tips;
	}
	
	@Override
	public Result<EmployeeHolder> employeeByToken(Client client, String token, int employeeId) {
		EmployeePO employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		UserPO user = null;
		switch (client) {
		case RECRUIT:
			DistributeSession session = new DistributeSession(token, redis);
			String uid = session.get(BtkjConsts.FIELD.UID);
			user = null == uid ? null : userMapper.getByKey(Integer.valueOf(uid));
		default:
			user = userMapper.userByToken(client, token);
		}
		if (null == user)
			return Consts.RESULT.TOKEN_INVALID;
		if (user.getUid() != employee.getUid())
			return Consts.RESULT.FORBID;
		AppPO app = appMapper.getByKey(employee.getAppId());
		TenantPO tenant = tenantMapper.getByKey(employee.getTid());
		return Result.result(new EmployeeHolder(app, user, tenant, employee));
	}
	
	@Override
	public Result<EmployeeHolder> employeeLockByToken(Client client, String token, int employeeId) {
		EmployeePO employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		UserPO user = null;
		String lockId = null;
		AppPO app = appMapper.getByKey(employee.getAppId());
		TenantPO tenant = tenantMapper.getByKey(employee.getTid());
		switch (client) {
		case RECRUIT:
			DistributeSession session = new DistributeSession(token, redis);
			String uid = session.get(BtkjConsts.FIELD.UID);
			user = null == uid ? null : userMapper.getByKey(Integer.valueOf(uid));
			lockId = null == user ? null : userMapper.lockUser(user.getUid());
			if (null == lockId)
				return Consts.RESULT.TOKEN_INVALID;
			break;
		default:
			Result<UserPO> result = userMapper.userLockByToken(client, token);
			if (!result.isSuccess())
				return Result.result(result.getCode(), result.getDesc(), null);
			user = result.attach();
			lockId = result.getDesc();
			break;
		}
		if (user.getUid() != employee.getUid())
			return Consts.RESULT.FORBID;
		return Result.result(Code.OK.id(), lockId, new EmployeeHolder(app, user, tenant, employee));
	}
	
	@Override
	public List<EmployeePO> team(Employee employee) {
		return employeeMapper.team(employee);
	}
}