package org.btkj.pojo.enums;

public enum DeliveryType {

	/**
	 * 快递
	 */
	EXPRESS(0),
	
	/**
	 * 网点自取
	 */
	ACTIVE_PICK(1),
	
	/**
	 * 网点配送
	 */
	DOT_DISPATCH(2);
	
	private int mark;
	
	private DeliveryType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final DeliveryType match(int type) {
		for (DeliveryType temp : DeliveryType.values()) {
			if (temp.mark != type)
				continue;
			return temp;
		}
		return null;
	}
}
