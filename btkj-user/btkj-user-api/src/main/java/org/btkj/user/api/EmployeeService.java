package org.btkj.user.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.EmployeeHolder;
import org.rapid.util.common.message.Result;

public interface EmployeeService {
	
	Employee employeeById(int employeeId);
	
	Map<Integer, Employee> employees(Collection<Integer> ids);
	
	EmployeeTip employeeTip(int id);
	
	Map<Integer, EmployeeTip> employeeTips(Collection<Integer> ids);
	
	Result<EmployeeHolder> employeeByToken(Client client, String token, int employeeId);
	
	Result<EmployeeHolder> employeeLockByToken(Client client, String token, int employeeId);
	 
	/**
	 * 我的团队
	 * @return
	 */
	List<Employee> team(int tid, int employeeId, int teamDepth);
	
	/**
	 * 获取用户拥有的雇员信息
	 * 
	 * @param user
	 * @return
	 */
	Map<Integer, EmployeeTip> employeeTips(App app, User user);
	
	/**
	 * 禁用
	 * 
	 * @param appId
	 * @param tid
	 * @param employeeId
	 * @return
	 */
	Result<Void> seal(int appId, int tid, int employeeId);
	
	/**
	 * 解禁
	 * 
	 * @param appId
	 * @param tid
	 * @param employeeId
	 * @return
	 */
	Result<Void> unseal(int appId, int tid, int employeeId);
}
