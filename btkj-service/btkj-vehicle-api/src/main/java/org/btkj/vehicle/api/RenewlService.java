package org.btkj.vehicle.api;

import org.btkj.pojo.model.EmployeeForm;

/**
 * 续保服务类：专门用来获取车辆的续保信息
 * 
 * @author ahab
 */
public interface RenewlService {

	/**
	 * 获取续保信息，必须要是员工才可以
	 * 
	 * @param employee
	 */
	void renewlInfo(EmployeeForm employeeForm, String license);
}
