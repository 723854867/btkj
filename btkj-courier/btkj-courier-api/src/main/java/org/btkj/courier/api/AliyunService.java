package org.btkj.courier.api;

import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.vo.StsInfo;

/**
 * aliyun 的一些服务
 * 
 * @author ahab
 *
 */
public interface AliyunService {

	/**
	 * STS assumeRole
	 * 
	 * @param userModel
	 * @return
	 */
	StsInfo assumeRole(User user);
	
	/**
	 * STS assumeRole
	 * 
	 * @param tenant
	 * @return
	 */
	StsInfo assumeRole(TenantPO tenant);
	
	/**
	 * STS assumeRole
	 * 
	 * @param employee
	 * @return
	 */
	StsInfo assumeRole(EmployeePO employee);
}
