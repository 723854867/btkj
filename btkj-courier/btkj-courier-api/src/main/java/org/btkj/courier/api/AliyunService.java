package org.btkj.courier.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.StsInfo;

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
	StsInfo assumeRole(App app);
	
	/**
	 * STS assumeRole
	 * 
	 * @param tenant
	 * @return
	 */
	StsInfo assumeRole(Tenant tenant);
	
	/**
	 * STS assumeRole
	 * 
	 * @param app
	 * @param user
	 * @return
	 */
	StsInfo assumeRole(User user);
	
	/**
	 * STS assumeRole
	 * 
	 * @param employee
	 * @return
	 */
	StsInfo assumeRole(Employee employee);
}
