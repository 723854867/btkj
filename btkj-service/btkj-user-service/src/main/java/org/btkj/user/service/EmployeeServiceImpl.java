package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.EmployeeTips;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.mapper.EmployeeMapper;
import org.btkj.user.redis.mapper.UserMapper;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public Result<EmployeeTips> employeeTips(int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		if (null == employee)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		User user = userMapper.getByKey(employee.getUid());
		EmployeeTips tips = new EmployeeTips(employeeId, user.getUid(), employee.getTid(), user.getName());
		return Result.result(tips);
	}
}