package org.btkj.user.api;

import java.util.List;

import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.po.EmployeePO;

public interface EmployeeService {
	
	EmployeeForm getById(int employeeId);
	 
	/**
	 * 我的团队
	 * @param form
	 * @return
	 */
	List<EmployeePO> team(EmployeeForm form);
}
