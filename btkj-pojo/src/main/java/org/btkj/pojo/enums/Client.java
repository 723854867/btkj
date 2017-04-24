package org.btkj.pojo.enums;

public enum Client {
	
	/**
	 * 移动 app 端
	 */
	APP(1),
	
	/**
	 * pc 端
	 */
	PC(2);
	
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
