package org.btkj.pojo.model;

/**
 * 只在保途 app 下使用
 * 
 * @author ahab
 *
 */
public enum TenantJoinApplyType {
	
	/**
	 * 游客，指的是还没注册过的用户，需要使用验证码校验 token 来申请加入
	 */
	TOURIST(0),
	
	/**
	 * 老用户：必须使用登录 token 来申请加入
	 */
	MEMBER(1);

	private int mark;
	private TenantJoinApplyType(int type) {
		this.mark = type;
	}
	public int mark() {
		return mark;
	}
	public static final TenantJoinApplyType match(int type) {
		for (TenantJoinApplyType temp : TenantJoinApplyType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
