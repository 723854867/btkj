package org.btkj.user.api;

import java.util.List;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.EmployeePO;

public interface EmployeeService {
	
	Employee employee(int employeeId);
	 
	/**
	 * 我的团队
	 * @param form
	 * @return
	 */
	List<EmployeePO> team(Employee employee);
}
