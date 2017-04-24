package org.btkj.user.api;

import org.btkj.pojo.enums.Client;
import org.rapid.util.common.message.Result;

public interface LoginService {

	/**
	 * 移动端的登录：必须要指定是哪个 app 登录
	 * 
	 * @param app 用户所属 app
	 * @param mobile 手机号
	 * @return
	 */
	Result<?> login(int appId, String mobile);
	
	/**
	 * pc 登录:必须要指定代理商
	 * 
	 * @param client
	 * @param code 用户所属 app
	 * @param mobile
	 * @param pwd
	 * @return
	 */
	Result<?> login(int appId, String mobile, String pwd);
	
	/**
	 * 注销
	 * 
	 * @param client
	 * @param token
	 * @return
	 */
	Result<?> logout(Client client, String token);
}
