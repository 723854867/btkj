package org.btkj.master.api;

import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.info.LoginInfo;
import org.rapid.util.common.message.Result;

/**
 * 保途云基础服务类
 * 
 * @author ahab
 */
public interface CloudService {

	/**
	 * 登录
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 */
	Result<LoginInfo> login(int id, String pwd);
	
	/**
	 * 登出
	 * 
	 * @param token
	 * @return
	 */
	void logout(String token);
	
	/**
	 * 通过 token 获取管理员
	 * 
	 * @param token
	 * @return
	 */
	Admin getAdministratorByToken(String token);
	
	/**
	 * 根据管路员工号获取管理员
	 * 
	 * @param id
	 * @return
	 */
	Admin admin(int id);
}
