package org.btkj.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.enums.BonusScaleType;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.PolicyNature;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.vo.JianJiePoliciesInfo.Insurance;

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
	
	/**
	 * 从简捷 GsUser 中获取业务员名字
	 * 
	 * @param gsUser
	 * @return
	 */
	public static final String nameFromJianJieGsUser(String gsUser) {
		return gsUser.substring(0, gsUser.indexOf("("));
	}
	
	/**
	 * 从简捷 GsUser 中获业务员ID
	 * 
	 * @param gsUser
	 * @return
	 */
	public static final String uidFromJianJieGsUser(String gsUser) {
		return gsUser.substring(gsUser.indexOf(":") + 1, gsUser.length() - 1);
	}
	
	/**
	 * 简捷险种转换成保途险种
	 * 
	 * @param insurances
	 * @return
	 */
	public static final Map<CommercialInsuranceType, org.btkj.pojo.bo.Insurance> jianJieInsuranceMapping(List<Insurance> insurances) {
		Map<CommercialInsuranceType, org.btkj.pojo.bo.Insurance> map = new HashMap<CommercialInsuranceType, org.btkj.pojo.bo.Insurance>();
		for (Insurance temp : insurances) {
			String name = temp.getName();
			if (name.contains("三者")) {
				map.put(CommercialInsuranceType.THIRD, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
				if (0 != temp.getMpBf()) 
					map.put(CommercialInsuranceType.THIRD_DEDUCTIBLE, new org.btkj.pojo.bo.Insurance(1, temp.getBf()));
			} else if (name.contains("车辆损失") || name.contains("车损失")) {
				map.put(CommercialInsuranceType.DAMAGE, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
				if (0 != temp.getMpBf()) 
					map.put(CommercialInsuranceType.DAMAGE_DEDUCTIBLE, new org.btkj.pojo.bo.Insurance(1, temp.getBf()));
			} else if (name.contains("司机") || name.contains("驾驶员") || name.contains("驾驶人")) {
				map.put(CommercialInsuranceType.DAMAGE, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
				if (0 != temp.getMpBf()) 
					map.put(CommercialInsuranceType.DAMAGE_DEDUCTIBLE, new org.btkj.pojo.bo.Insurance(1, temp.getBf()));
			} else if (name.contains("乘客")) {
				map.put(CommercialInsuranceType.PASSENGER, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
				if (0 != temp.getMpBf()) 
					map.put(CommercialInsuranceType.PASSENGER_DEDUCTIBLE, new org.btkj.pojo.bo.Insurance(1, temp.getBf()));
			} else if (name.contains("盗抢")) {
				map.put(CommercialInsuranceType.ROBBERY, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
				if (0 != temp.getMpBf()) 
					map.put(CommercialInsuranceType.ROBBERY_DEDUCTIBLE, new org.btkj.pojo.bo.Insurance(1, temp.getBf()));
			} else if (name.contains("玻璃")) 
				map.put(CommercialInsuranceType.GLASS, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
			else if (name.contains("自燃")) {
				map.put(CommercialInsuranceType.AUTO_FIRE, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
				if (0 != temp.getMpBf()) 
					map.put(CommercialInsuranceType.AUTO_FIRE_DEDUCTIBLE, new org.btkj.pojo.bo.Insurance(1, temp.getBf()));
			} else if (name.contains("划痕")) {
				map.put(CommercialInsuranceType.SCRATCH, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
				if (0 != temp.getMpBf()) 
					map.put(CommercialInsuranceType.SCRATCH_DEDUCTIBLE, new org.btkj.pojo.bo.Insurance(1, temp.getBf()));
			} else if (name.contains("修理")) 
				map.put(CommercialInsuranceType.GARAGE_DESIGNATED, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
			else if (name.contains("无法")) 
				map.put(CommercialInsuranceType.UNKNOWN_THIRD, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
			else if (name.contains("涉水")) {
				map.put(CommercialInsuranceType.WADDING, new org.btkj.pojo.bo.Insurance(temp.getBe(), temp.getBf()));
				if (0 != temp.getMpBf()) 
					map.put(CommercialInsuranceType.WADDING_DEDUCTIBLE, new org.btkj.pojo.bo.Insurance(1, temp.getBf()));
			}
		}
		return map;
	}
	
	/**
	 * 根据无赔款优惠系数来获取(几)年无赔，或者一年出险(几)次
	 * 
	 * @param rate
	 * @return
	 */
	public static final int noLossDiscountRateAnalysis(String rate) {
		BigDecimal decimal = new BigDecimal(rate);
		if (decimal.compareTo(new BigDecimal(0.85)) >= 0 && decimal.compareTo(new BigDecimal(1.25)) < 0)
			return 1;
		if ((decimal.compareTo(new BigDecimal(0.7)) >= 0 && decimal.compareTo(new BigDecimal(0.85)) < 0)
				|| (decimal.compareTo(new BigDecimal(1.25)) >= 0 && decimal.compareTo(new BigDecimal(1.5)) < 0))
			return 2;
		return 3;
	}
	
	public static final VehicleUsedType vehicleUsedTypeFromBiHuUsedType(int biHuUsedType) {
		switch (biHuUsedType) {
		case 1:
			return VehicleUsedType.HOME_USE;
		case 2:
			return VehicleUsedType.ORGAN;
		case 3:
			return VehicleUsedType.ENTERPRISE;
		case 4:
			return null;
		case 5:
			return VehicleUsedType.LEASE;
		case 6:
			return VehicleUsedType.BIZ_TRUCK;
		case 7:
			return VehicleUsedType.NO_BIZ_TRUCK;
		case 8:
			return VehicleUsedType.CITY_BUS;
		default:
			return VehicleUsedType.HOME_USE;
		}
	}
}
