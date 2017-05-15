package org.btkj.user.api;

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
	Result<Void> employeeDisable(int employeeId, EmployeeState state);
	
	/**
	 * 根据id获取雇员详细信息
	 * @return
	 */
	EmployeeInfo employeeInfoRead(int employeeId);
	
	/**
	 * 修改保存雇员基本信息
	 * @return
	 */
	Result<Void> employeeInfoSave(EmployeeInfo employeeInfo);
}
