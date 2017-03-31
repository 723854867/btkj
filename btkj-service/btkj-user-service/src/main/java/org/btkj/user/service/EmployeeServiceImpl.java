package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Employee;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.redis.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public Employee getEmplyee(int uid, int tid) {
		return employeeMapper.getByUidAndTid(uid, tid);
	}
}
