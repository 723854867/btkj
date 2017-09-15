package org.btkj.user.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Employee.Mod;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.EmployeeHolder;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.mybatis.Tx;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.data.storage.redis.DistributeSession;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	private Tx tx;
	@Resource
	private Redis redis;
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public Employee employeeById(int employeeId) {
		return employeeMapper.getByKey(employeeId);
	}
	
	@Override
	public Map<Integer, Employee> employees(Collection<Integer> ids) {
		return employeeMapper.getByKeys(ids);
	}
	
	@Override
	public EmployeeTip employeeTip(int id) {
		Employee employee = employeeMapper.getByKey(id);
		if (null == employee)
			return null;
		App app = appMapper.getByKey(employee.getAppId());
		User user = userMapper.getByKey(employee.getUid());
		Tenant tenant = tenantMapper.getByKey(employee.getTid());
		return new EmployeeTip(employee, app, user, tenant);
	}
	
	@Override
	public Map<Integer, EmployeeTip> employeeTips(Collection<Integer> ids) {
		Map<Integer, Employee> employees = employeeMapper.getByKeys(ids);
		Map<Integer, EmployeeTip> tips = new HashMap<Integer, EmployeeTip>();
		if (CollectionUtil.isEmpty(employees))
			return tips;
		Set<Integer> uids = new HashSet<Integer>();
		Set<Integer> tids = new HashSet<Integer>();
		Set<Integer> appIds = new HashSet<Integer>();
		for (Employee po : employees.values()) {
			uids.add(po.getUid());
			tids.add(po.getTid());
			appIds.add(po.getAppId());
		}
		Map<Integer, App> apps = appMapper.getByKeys(appIds);
		Map<Integer, User> users = userMapper.getByKeys(uids);
		Map<Integer, Tenant> tenants = tenantMapper.getByKeys(tids);
		for (Employee po : employees.values()) 
			tips.put(po.getId(), new EmployeeTip(po, apps.get(po.getAppId()), users.get(po.getUid()), tenants.get(po.getTid())));
		return tips;
	}
	
	@Override
	public Result<EmployeeHolder> employeeByToken(Client client, String token, int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		User user = null;
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
		App app = appMapper.getByKey(employee.getAppId());
		Tenant tenant = tenantMapper.getByKey(employee.getTid());
		return Result.result(new EmployeeHolder(app, user, tenant, employee));
	}
	
	@Override
	public Result<EmployeeHolder> employeeLockByToken(Client client, String token, int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		User user = null;
		String lockId = null;
		App app = appMapper.getByKey(employee.getAppId());
		Tenant tenant = tenantMapper.getByKey(employee.getTid());
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
			Result<User> result = userMapper.userLockByToken(client, token);
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
	public List<Employee> team(int tid, int employeeId, int teamDepth) {
		return tx.team(tid, employeeId, teamDepth);
	}
	
	@Override
	public Map<Integer, EmployeeTip> employeeTips(App app, User user) {
		Map<Integer, Employee> employees = employeeMapper.ownedTenants(user.getUid());
		if (CollectionUtil.isEmpty(employees))
			return Collections.EMPTY_MAP;
		Set<Integer> set = new HashSet<Integer>();
		for (Employee employee : employees.values())
			set.add(employee.getTid());
		Map<Integer, Tenant> tenants = tenantMapper.getByKeys(set);
		Map<Integer, EmployeeTip> map = new HashMap<Integer, EmployeeTip>();
		for (Employee employee : employees.values())
			map.put(employee.getId(), new EmployeeTip(employee, app, user, tenants.get(employee.getTid())));
		return map;
	}
	
	@Override
	public Result<Void> seal(int appId, int tid, int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (employee.getAppId() != appId || employee.getTid() != tid || BtkjUtil.isTopRole(employee))
			return Consts.RESULT.FORBID;
		if (!Mod.SEAL.satisfy(employee.getMod())) {
			employee.setMod(employee.getMod() | Mod.SEAL.mark());
			employee.setUpdated(DateUtil.currentTime());
			employeeMapper.update(employee);
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> unseal(int appId, int tid, int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (employee.getAppId() != appId || employee.getTid() != tid)
			return Consts.RESULT.FORBID;
		if (Mod.SEAL.satisfy(employee.getMod())) {
			employee.setMod(employee.getMod() & (~Mod.SEAL.mark()));
			employee.setUpdated(DateUtil.currentTime());
			employeeMapper.update(employee);
		}
		return Consts.RESULT.OK;
	}
}