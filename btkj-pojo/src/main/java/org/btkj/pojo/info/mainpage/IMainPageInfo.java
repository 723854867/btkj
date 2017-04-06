package org.btkj.pojo.info.mainpage;

import java.io.Serializable;

/**
 * 首页信息
 * 
 * @author ahab
 */
public interface IMainPageInfo extends Serializable {

	/**
	 * 获取用户唯一标识 ID
	 * 
	 * @return
	 */
	int getUid();
	
	/**
	 * 设置用户唯一标识 ID
	 * 
	 * @param uid
	 */
	void setUid(int uid);
}
