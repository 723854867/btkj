package org.btkj.user.api;

import org.btkj.pojo.entity.Employee;

/**
 * 获取雇员信息
 * 
 * @author ahab
 */
public interface EmployeeService {

	/**
	 * 获取雇员信息
	 * 
	 * @param uid
	 * @return
	 */
	Employee getEmplyee(int uid, int tid);
}
