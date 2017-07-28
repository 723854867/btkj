package org.btkj.user.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.user.pojo.model.EmployeeHolder;
import org.rapid.util.common.message.Result;

public interface EmployeeService {
	
	Employee employee(int employeeId);
	
	Map<Integer, EmployeeTip> employeeTips(Collection<Integer> ids);
	
	Result<EmployeeHolder> employeeByToken(Client client, String token, int employeeId);
	
	Result<EmployeeHolder> employeeLockByToken(Client client, String token, int employeeId);
	 
	/**
	 * 我的团队
	 * @param form
	 * @return
	 */
	List<EmployeePO> team(Employee employee);
}
