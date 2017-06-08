package org.btkj.user.api;

import java.util.List;

import org.btkj.pojo.entity.Employee;
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
	 * 我的团队
	 * @param form
	 * @return
	 */
	List<Employee> team(EmployeeForm form);
}
