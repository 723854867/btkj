package org.btkj.vehicle.pojo.enums;

import java.math.BigDecimal;

import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.enums.PolicyNature;
import org.btkj.vehicle.pojo.entity.CoefficientRange;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.math.compare.BigDecimalComparable;
import org.rapid.util.math.compare.Comparison;
import org.rapid.util.math.compare.IntComparable;

public enum CoefficientType {

	/**
	 * 理赔次数(出险次数)
	 */
	CLAIMS(1, "上年出险次数") {
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			return false;
		}
	},
	
	/**
	 * 无赔次数
	 */
	NO_CLAIMS(2, "无赔年数") {
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			return false;
		}
	},
	
	/**
	 * 性别
	 */
	GENDER(3, "性别") {
		@Override
		public boolean checkValue(Comparison symbol, String[] value) {
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
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			return false;
		}
	},
	
	/**
	 * 转续保
	 */
	ZXB(4, "转续保") {
		@Override
		public boolean checkValue(Comparison symbol, String[] value) {
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
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			return false;
		}
	},

	/**
	 * 座位
	 */
	SEAT_COUNT(5, "座位") {
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			return IntComparable.SINGLETON.compare(Comparison.match(range.getComparison()), order.getTips().getSeat(), CollectionUtil.toIntegerArray(range.getComparableValue().split(Consts.SYMBOL_UNDERLINE)));
		}
	},
	
	
	/**
	 * 车牌
	 */
	LICENSE(6, "车牌") {
		@Override
		public boolean checkValue(Comparison symbol, String[] value) {
			try {
				switch (symbol) {
				case eq:
					if (null == value || value.length != 1 || value[0].length() != 2)
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
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			String license = order.getTips().getLicense();
			String src = license.substring(0, 1);
			return range.getComparableValue().equals(src);
		}
	},
	
	LICENSE_HEAD(10, "车牌头") {
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			String license = order.getTips().getLicense();
			String src = license.substring(0, 2);
			return range.getComparableValue().equals(src);
		}
	},
	
	/**
	 * 车龄
	 */
	VEHICLE_AGE(7, "车龄") {
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			return false;
		}
	},
	
	/**
	 * 购置价
	 */
	PURCHASE_PRICE(8, "购置价") {
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			return BigDecimalComparable.SINGLETON.compare(Comparison.match(range.getComparison()), new BigDecimal(order.getTips().getPrice()), CollectionUtil.toBigDecimalArray(range.getComparableValue().split(Consts.SYMBOL_UNDERLINE)));
		}
	},
	
	/**
	 * 年龄
	 */
	AGE(9, "年龄") {
		@Override
		public boolean satisfy(CoefficientRange range, VehicleOrder order) {
			return IntComparable.SINGLETON.compare(Comparison.match(range.getComparison()), 0, CollectionUtil.toIntegerArray(range.getComparableValue().split(Consts.SYMBOL_UNDERLINE)));
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
	
	/**
	 * 默认检测的是数值类型
	 * 
	 * @param symbol
	 * @param value
	 * @return
	 */
	public boolean checkValue(Comparison symbol, String[] value) {
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
	
	public abstract boolean satisfy(CoefficientRange range, VehicleOrder order);
	
	public static final CoefficientType match(int type) {
		for (CoefficientType temp : CoefficientType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
