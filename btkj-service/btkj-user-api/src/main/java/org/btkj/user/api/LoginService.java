package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.LoginInfo;
import org.rapid.util.common.message.Result;

public interface LoginService {

	/**
	 * 移动端的登录
	 * 
	 * @param app 用户所属 app
	 * @param mobile 手机号
	 * @return
	 */
	Result<LoginInfo> login(App app, String mobile);
	
	/**
	 * 浏览器登录
	 * 
	 * @return
	 */
	Result<LoginInfo> login(Client client, App app, Tenant tenant, String mobile, String pwd);
}
