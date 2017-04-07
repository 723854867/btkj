package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.LoginInfo;
import org.rapid.util.common.message.Result;

public interface LoginService {

	/**
	 * 移动端的登录
	 * 
	 * @param app 用户所属 app
	 * @param tenant 用户所属租户(如果是保途 app 则为 null)
	 * @param mobile 手机号
	 * @return
	 */
	Result<LoginInfo> appLogin(App app, Tenant tenant, String mobile);
	
	/**
	 * pc 端的登录
	 * 
	 * @return
	 */
	Result<LoginInfo> pcLogin(App app, Tenant tenant, String mobile, String pwd);
	
	/**
	 * 管理后台的登录
	 * 
	 * @return
	 */
	Result<LoginInfo> managerLogin(App app, Tenant tenant, String mobile, String pwd);
	
	/**
	 * 仅限保途 app
	 * 
	 * @param token 登录 token
	 * @param tenant 代理商
	 * @param chief 上级用户 
	 * @return
	 */
	Result<?> apply(String token, Tenant tenant, User chief);
	
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
	Result<?> apply(App app, Tenant tenant, String mobile, String name, String identity, User chief);
}
