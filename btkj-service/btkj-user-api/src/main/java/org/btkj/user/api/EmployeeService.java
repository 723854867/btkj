package org.btkj.user.api;

import org.btkj.pojo.info.tips.EmployeeTips;
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
	Result<Pager<EmployeeTips>> employeeList(int tid, int page, int pageSize);
}
