package org.btkj.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.AppMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
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
	public List<EmployeePO> team(Employee employee) {
		return employeeMapper.team(employee, employee.getTeamDepth());
	}
}