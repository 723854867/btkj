package org.btkj.pojo.enums;

import org.rapid.util.lang.StringUtil;
import org.rapid.util.math.compare.ComparisonSymbol;

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
	GENDER(3, "性别") {
		@Override
		public boolean checkValue(ComparisonSymbol symbol, String[] value) {
			try {
				switch (symbol) {
				case eq:
					if (null == value || value.length != 1)
						return false;
					int val = Integer.valueOf(value[0]);
					return null != org.rapid.util.common.enums.GENDER.match(val);
				default:
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
	},
	
	/**
	 * 转续保
	 */
	ZXB(4, "转续保") {
		@Override
		public boolean checkValue(ComparisonSymbol symbol, String[] value) {
			try {
				switch (symbol) {
				case eq:
					if (null == value || value.length != 1)
						return false;
					int val = Integer.valueOf(value[0]);
					return null != PolicyNature.match(val);
				default:
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
	},

	/**
	 * 座位
	 */
	SEAT_COUNT(5, "座位"),
	
	
	/**
	 * 车牌
	 */
	LICENSE(6, "车牌") {
		@Override
		public boolean checkValue(ComparisonSymbol symbol, String[] value) {
			try {
				switch (symbol) {
				case eq:
					if (null == value || value.length != 1 || value.length != 2)
						return false;
					boolean find = false;
					for (String temp : StringUtil.PROVINCES) {
						if (temp.equals(value[0].substring(0, 1))) {
							find = true;
							break;
						}
					}
					if (!find)
						return false;
					for (String temp : StringUtil.ALPHABET) {
						if (temp.equals(value[0].substring(1, 2)))
							return true;
					}
					return false;
				default:
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
	},
	
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
	
	/**
	 * 默认检测的是数值类型
	 * 
	 * @param symbol
	 * @param value
	 * @return
	 */
	public boolean checkValue(ComparisonSymbol symbol, String[] value) {
		try {
			switch (symbol) {
			case gt:
			case gte:
			case lt:
			case lte:
			case eq:
				if (null == value || value.length != 1)
					return false;
				Integer.valueOf(value[0]);
				return true;
			case bteween:
			case lbteween:
			case rbteween:
				if (null == value || value.length != 2)
					return false;
				int min = Integer.valueOf(value[0]);
				int max = Integer.valueOf(value[1]);
				return max > min;
			default:
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	};
	
	public static final CoefficientType match(int type) {
		for (CoefficientType temp : CoefficientType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
