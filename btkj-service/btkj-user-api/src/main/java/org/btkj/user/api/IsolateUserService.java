package org.btkj.user.api;

import java.io.Serializable;

import org.rapid.util.common.message.Result;

/**
 * 独立 app 的用户服务
 * 
 * @author ahab
 */
public interface IsolateUserService {
	
	/**
	 * 通过手机登录
	 * 
	 * @param appId
	 * @return
	 */
	Result<Serializable> loginWithMobile(int appId, int tid, String mobile);
	
	/**
	 * 申请加入公司
	 * 
	 * @param appId app_id
	 * @param tid 代理商 id
	 * @param mobile 手机号
	 * @param name 姓名
	 * @param identity 身份证
	 * @param chief 上级用户 uid
	 * @return
	 */
	Result<Void> apply(int appId, int tid, String mobile, String name, String identity, int chief);
}
