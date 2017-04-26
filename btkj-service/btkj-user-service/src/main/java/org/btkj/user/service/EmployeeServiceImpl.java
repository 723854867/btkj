package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.EmployeeTips;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public Employee getByTidAndUid(int tid, int uid) {
		return employeeMapper.getByTidAndUid(tid, uid);
	}

	@Override
	public Result<Pager<EmployeeTips>> employeeList(int tid, int page, int pageSize) {
		return employeeMapper.employeeList(tid, page, pageSize);
	}
	
	@Override
	public Result<EmployeeTips> employeeTips(int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		User user = userMapper.getByKey(employee.getUid());
		Tenant tenant = tenantMapper.getByKey(employee.getTid());
		EmployeeTips tips = new EmployeeTips(tenant, employee, user);
		return Result.result(tips);
	}
}