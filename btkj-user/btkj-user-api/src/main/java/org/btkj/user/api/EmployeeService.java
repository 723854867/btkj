package org.btkj.user.api;

import java.util.List;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.enums.EmployeeState;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.pojo.info.EmployeeListInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.EmployeeSearcher;
import org.rapid.util.common.message.Result;

public interface EmployeeService {
	
	EmployeeForm getById(int employeeId);
	
	/**
	 * 代理公司获取员工列表
	 * 
	 * @return
	 */
	Result<Pager<EmployeeListInfo>> employeeList(EmployeeSearcher searcher);
	
	/**
	 * 禁用雇员
	 * 
	 * @param state
	 * @return
	 */
	Result<Void> employeeState(int employeeId, EmployeeState state);
	
	/**
	 * 根据id获取雇员详细信息
	 * @return
	 */
	EmployeeInfo employeeInfo(int employeeId);
	
	/**
	 * 修改保存雇员基本信息
	 * @return
	 */
	Result<Void> employeeEdit(EmployeeInfo employeeInfo);
	
	/**
	 * 我的团队
	 * @param form
	 * @return
	 */
	Pager<Employee> team(EmployeeForm form);
	
	/**
	 * 获取多个雇员
	 * 
	 * @param ids
	 * @return
	 */
	List<Employee> employees(List<Integer> ids);
}
