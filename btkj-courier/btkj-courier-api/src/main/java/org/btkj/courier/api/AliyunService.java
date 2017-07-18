package org.btkj.courier.api;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
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
	StsInfo assumeRole(AppPO app);
	
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
	 * @param app
	 * @param user
	 * @return
	 */
	StsInfo assumeRole(UserPO user);
	
	/**
	 * STS assumeRole
	 * 
	 * @param employee
	 * @return
	 */
	StsInfo assumeRole(EmployeePO employee);
}
