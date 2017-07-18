package org.btkj.courier.api;

import org.btkj.courier.pojo.info.StsInfo;

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
	StsInfo assumeRole(int appId, int uid);
}
