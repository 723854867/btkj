package org.btkj.user.api;

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
	 * @return
	 */
	Result employeeDisable(int id);
}
