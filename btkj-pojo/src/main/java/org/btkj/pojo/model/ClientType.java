package org.btkj.pojo.model;

public enum ClientType {
	
	/**
	 * 移动 app 端
	 */
	APP(1),
	
	/**
	 * pc 端
	 */
	PC(2),

	/**
	 * 管理后台
	 */
	MANAGER(4);
	
	private int type;
	
	private ClientType(int type) {
		this.type = type;
	}
	
	public int type() {
		return type;
	}
	
	public boolean contains(int mod) { 
		return (mod & this.type) == this.type;
	}
	
	public static final ClientType match(int type) {
		for (ClientType temp : ClientType.values()) {
			if (temp.type == type)
				return temp;
		}
		throw new IllegalArgumentException("clientType");
	}
}
