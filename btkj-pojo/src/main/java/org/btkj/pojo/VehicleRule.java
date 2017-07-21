package org.btkj.pojo;

import org.btkj.pojo.enums.BonusScaleType;
import org.btkj.pojo.enums.PolicyNature;

public class VehicleRule {

	/**
	 * 根据简捷的的使用性质和车辆类型来返回车辆规模类型，用在规模佣金计算中
	 * 
	 * @param info
	 * @return
	 */
	public static final BonusScaleType scaleTypeFromJianJie(String ssxz, String cllx) {
		if (ssxz.contains("非营") || ssxz.contains("机关") || ssxz.contains("自用")) {
			if (cllx.contains("座") || cllx.contains("客车") || cllx.contains("轿车"))
				return BonusScaleType.NO_PROFIT_COACH;
			else if (cllx.contains("挂车") || cllx.contains("货车") || cllx.contains("载货"))
				return BonusScaleType.NO_PROFIT_TRUCK;
			else
				return BonusScaleType.OTHER;
		} else if (ssxz.contains("营业") || ssxz.contains("营运")) {
			if (cllx.contains("座") || cllx.contains("客车") || cllx.contains("轿车"))
				return BonusScaleType.PROFT_COACH;
			else if (cllx.contains("挂车") || cllx.contains("货车") || cllx.contains("载货"))
				return BonusScaleType.PROFIT_TRUCK;
			else
				return BonusScaleType.OTHER;
		} else 
			return BonusScaleType.OTHER;
	}
	
	/**
	 * 根据简捷返回的转续保状态字段获取保途的转续保枚举类型
	 * 
	 * @return
	 */
	public static final PolicyNature natureFromJianJie(String baseStatus) {
		if (baseStatus.equals("新"))
			return PolicyNature.NEW;
		else if (baseStatus.equals("转"))
			return PolicyNature.RE_INSURANCE;
		else if (baseStatus.equals("续"))
			return PolicyNature.RENEWAL;
		else
			throw new RuntimeException("简捷续保状态错误 ： " + baseStatus);
	}
}
