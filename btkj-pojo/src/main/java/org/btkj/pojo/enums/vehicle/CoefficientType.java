package org.btkj.pojo.enums.vehicle;

public enum CoefficientType {

	/**
	 * 理赔次数(出险次数)
	 */
	CLAIMS(1, "上年出险次数"),
	
	/**
	 * 无赔次数
	 */
	NO_CLAIMS(2, "无赔年数"),
	
	/**
	 * 性别
	 */
	GENDER(3, "性别"),
	
	/**
	 * 转续保
	 */
	ZXB(4, "转续保"),

	/**
	 * 座位
	 */
	SEAT_COUNT(5, "座位"),
	
	/**
	 * 车牌
	 */
	LICENSE(6, "车牌"),
	
	/**
	 * 车龄
	 */
	VEHICLE_AGE(7, "车龄") {
		@Override
		public boolean isCustom() {
			return true;
		}
		@Override
		public int maxCustomNum() {
			return 4;
		}
	},
	
	/**
	 * 购置价
	 */
	PURCHASE_PRICE(8, "购置价") {
		@Override
		public boolean isCustom() {
			return true;
		}
		@Override
		public int maxCustomNum() {
			return 4;
		}
	},
	
	/**
	 * 年龄
	 */
	AGE(9, "年龄") {
		@Override
		public boolean isCustom() {
			return true;
		}
		@Override
		public int maxCustomNum() {
			return 4;
		}
	};
	
	private int mark;
	private String title;
	
	private CoefficientType(int mark, String title) {
		this.mark = mark;
		this.title = title;
	}
	
	public int mark() {
		return mark;
	}
	
	public String title() {
		return title;
	}
	
	public boolean isCustom() {
		return false;
	}
	
	public int maxCustomNum() {
		return 0;
	}
	
	public static final CoefficientType match(int type) {
		for (CoefficientType temp : CoefficientType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
