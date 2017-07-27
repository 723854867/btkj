package org.btkj.user.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.lang.CollectionUtil;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	private AppMapper appMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
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
	public List<EmployeePO> team(Employee employee) {
		return employeeMapper.team(employee);
	}
}