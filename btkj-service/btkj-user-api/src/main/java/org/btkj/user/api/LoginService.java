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
	Result<LoginInfo> appLogin(App app, String mobile);
	
	/**
	 * 浏览器登录
	 * 
	 * @return
	 */
	Result<LoginInfo> browserLogin(Client client, App app, Tenant tenant, String mobile, String pwd);
	
	/**
	 * 仅限保途 app
	 * 
	 * @param token 登录 token
	 * @param employeeId 上级的雇员ID
	 * @return
	 */
	Result<?> apply(String token, int employeeId);
	
	/**
	 * 申请加入公司
	 * 
	 * @param app app
	 * @param tenant 代理商 
	 * @param mobile 手机号
	 * @param name 姓名
	 * @param identity 身份证
	 * @param chief 上级用户
	 * @return
	 */
	Result<?> apply(int appId, String mobile, String name, String identity, int employeeId);
}
