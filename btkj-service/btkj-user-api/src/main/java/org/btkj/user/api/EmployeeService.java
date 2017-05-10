package org.btkj.user.api;

import org.btkj.pojo.info.EmoloyeeInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

public interface EmployeeService {
	
	EmployeeForm getById(int employeeId);
	
	/**
	 * 代理公司获取员工列表
	 * 
	 * @return
	 */
	Result<Pager<EmoloyeeInfo>> employeeList(int tid, int page, int pageSize);
	
	/**
	 * 禁用雇员
	 * 
	 * @return
	 */
	Result employeeDisable(int id);
}
