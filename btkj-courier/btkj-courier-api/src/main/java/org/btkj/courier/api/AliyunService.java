package org.btkj.courier.api;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.info.courier.StsInfo;

/**
 * aliyun 的一些服务
 * 
 * @author ahab
 *
 */
public interface AliyunService {

	/**
	 * 用户的 STS token
	 * 
	 * @return
	 */
	StsInfo assumeRole(int appId, int uid);
	
	/**
	 * 商户的 STS token
	 * 
	 * @param appId
	 * @param tid
	 * @return
	 */
	StsInfo assumeRole(App app, Tenant tenant);
}
