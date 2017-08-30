package org.btkj.vehicle.pojo.enums;

import org.rapid.util.math.compare.Comparison;

public enum PoundageCoefficientType {

	/**
	 * 理赔次数(出险次数)
	 */
	CLAIMS,
	
	/**
	 * 无赔次数
	 */
	NO_CLAIMS,
	
	/**
	 * 性别
	 */
	GENDER,
	
	/**
	 * 转续保
	 */
	ZXB,

	/**
	 * 座位
	 */
	SEAT_COUNT,
	
	/**
	 * 车牌号
	 */
	LICENSE,
	
	/**
	 * 车牌号前两位
	 */
	LICENSE_HEAD,
	
	/**
	 * 车龄
	 */
	VEHICLE_AGE,
	
	/**
	 * 购置价
	 */
	PRICE,
	
	/**
	 * 年龄
	 */
	AGE;
	
	public boolean checkValue(Comparison comparison, String[] values) {
		switch (comparison) {
		case gt:
		case gte:
		case lt:
		case lte:
		case eq:
			if (values.length != 1)
				return false;
			String val = values[0];
			try {
				Integer.valueOf(val);
			} catch (Exception e) {
				return false;
			}
			return true;
		case bteween:
		case lbteween:
		case rbteween:
			if (values.length != 2)
				return false;
			try {
				int min = Integer.valueOf(values[0]);
				int max = Integer.valueOf(values[1]);
				return max > min;
			} catch (Exception e) {
				return false;
			}
		default:
			return false;
		}
	}
}
