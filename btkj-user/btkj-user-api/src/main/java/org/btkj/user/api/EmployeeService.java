package org.btkj.user.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.user.pojo.model.EmployeeHolder;
import org.rapid.util.common.message.Result;

public interface EmployeeService {
	
	EmployeePO employeeById(int employeeId);
	
	Employee employee(int employeeId);
	
	Map<Integer, EmployeePO> employees(Collection<Integer> ids);
	
	EmployeeTip employeeTip(int id);
	
	Map<Integer, EmployeeTip> employeeTips(Collection<Integer> ids);
	
	Result<EmployeeHolder> employeeByToken(Client client, String token, int employeeId);
	
	Result<EmployeeHolder> employeeLockByToken(Client client, String token, int employeeId);
	
	/**
	 * 加入商户检测
	 * 
	 * @param uid
	 * @param parentId
	 * @return
	 */
	Result<Void> tenantJoinCheck(int uid, int parentId);
	 
	/**
	 * 我的团队
	 * @return
	 */
	List<EmployeePO> team(int tid, int employeeId, int teamDepth);
}
