package org.btkj.user.api;

import java.util.List;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.model.EmployeeForm;

public interface EmployeeService {
	
	EmployeeForm getById(int employeeId);
	 
	/**
	 * 我的团队
	 * @param form
	 * @return
	 */
	List<Employee> team(EmployeeForm form);
}
