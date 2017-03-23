package org.btkj.user.api;

import java.io.Serializable;

import org.rapid.util.common.message.Result;

/**
 * 保途用户服务
 * 
 * @author ahab
 */
public interface BtkjUserService {
	
	/**
	 * 通过手机登录
	 * 
	 * @param appId
	 * @return
	 */
	Result<Serializable> loginWithMobile(String mobile);
	
	/**
	 * 用户已经存在的情况下申请加入代理公司
	 * 
	 * @param token 登录 token
	 * @param tid 代理商 id
	 * @param chief 上级用户 uid
	 * @return
	 */
	Result<?> apply(String token, int tid, int chief);
	
	/**
	 * 用户首次申请加入代理公司(这时用户还没存在)
	 * 
	 * @param tid 代理商 id
	 * @param mobile 手机
	 * @param name 姓名
	 * @param identity 身份证
	 * @param chief 上级用户 uid
	 * @return
	 */
	Result<?> apply(int tid, String mobile, String name, String identity, int chief);
}
