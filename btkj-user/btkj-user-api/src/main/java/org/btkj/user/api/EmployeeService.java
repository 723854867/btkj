package org.btkj.user.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.vo.EmployeeTip;

public interface EmployeeService {
	
	Employee employee(int employeeId);
	
	Map<Integer, EmployeeTip> employeeTips(Collection<Integer> ids);
	 
	/**
	 * 我的团队
	 * @param form
	 * @return
	 */
	List<EmployeePO> team(Employee employee);
}
