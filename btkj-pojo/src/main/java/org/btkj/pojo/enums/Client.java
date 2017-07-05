package org.btkj.pojo.enums;

public enum Client {
	
	/**
	 * 移动 app 端
	 */
	APP(1),
	
	/**
	 * 商户管理端
	 */
	TENANT_MANAGER(2),
	
	/**
	 * 保途管理端
	 * 
	 */
	BAO_TU_MANAGER(4),
	
	/**
	 * h5推荐好友端
	 */
	RECRUIT(8);
	
	private int type;
	
	private Client(int type) {
		this.type = type;
	}
	
	public int type() {
		return type;
	}
	
	public static final Client match(int type) {
		for (Client temp : Client.values()) {
			if (temp.type == type)
				return temp;
		}
		return null;
	}
}
