package org.btkj.user.redis.mapper;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Employee;
import org.btkj.user.BaseTest;
import org.junit.Test;

public class EmployeeMapperTest extends BaseTest {

	@Resource
	private EmployeeMapper employeeMapper;
	
	@Test
	public void testGetByUidAndTid() {
		Employee employee = employeeMapper.getByUidAndTid(1, 1000);
		System.out.println(employee);
	}
}
