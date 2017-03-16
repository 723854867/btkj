package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.InviterModel;
import org.btkj.pojo.model.UserLoginModel;
import org.rapid.util.common.message.Result;

public interface UserService {
	
	/**
	 * 通过 uid 获取用户
	 * 
	 * @param uid
	 * @return
	 */
	User getUserByUid(int uid);
	
	/**
	 * 通过 token 获取用户并且同时获取用户的资源锁
	 * 
	 * @param token
	 * @return
	 */
	Result<User> lockUserByToken(App app, String token);
	
	/**
	 * 通过手机登录
	 * 
	 * @param appId
	 * @param mobile
	 * @return
	 */
	Result<UserLoginModel> loginWithMobile(int appId, String mobile);
	
	/**
	 * 游客申请加入租户
	 * 
	 * @param inviter 邀请者
	 * @param token 验证码校验 token
	 * @param name 姓名
	 * @param identity 身份证
	 */
	void touristJoinTenantApply(InviterModel inviter, String token, String name, String identity);
	
	/**
	 * 会员申请加入租户：只有保途 app 的用户才可以调用该接口
	 * 
	 * @param inviter 邀请者
	 * @param token 登录 token
	 */
	void memberJoinTenantApply(InviterModel inviter, String mobile);
	
	/**
	 * 招募验证
	 * 
	 * @param appId
	 * @param tid
	 * @param mobile
	 * @return
	 */
	boolean recruitAuth(int appId, int tid, String mobile);
}
