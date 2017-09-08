package org.btkj.pojo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.btkj.pojo.entity.user.EmployeePO.Mod;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.enums.IDType;
import org.btkj.pojo.enums.PolicyNature;
import org.btkj.pojo.enums.VehicleBonusType;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.info.JianJiePoliciesInfo.Insurance;
import org.btkj.pojo.info.VehiclePolicyTips;
import org.btkj.pojo.model.PolicySchema;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;

public class VehicleUtil {

	/**
	 * 根据简捷的的使用性质和车辆类型来返回车辆规模类型，用在规模佣金计算中
	 * 
	 * @param info
	 * @return
	 */
	public static final VehicleBonusType bonusTypeFromJianJie(String ssxz, String cllx) {
		if (ssxz.contains("非营") || ssxz.contains("机关") || ssxz.contains("自用")) {
			if (cllx.contains("座") || cllx.contains("客车") || cllx.contains("轿车"))
				return VehicleBonusType.NPC;
			else if (cllx.contains("挂车") || cllx.contains("货车") || cllx.contains("载货"))
				return VehicleBonusType.NPT;
			else
				return VehicleBonusType.OTHER;
		} else if (ssxz.contains("营业") || ssxz.contains("营运")) {
			if (cllx.contains("座") || cllx.contains("客车") || cllx.contains("轿车"))
				return VehicleBonusType.PC;
			else if (cllx.contains("挂车") || cllx.contains("货车") || cllx.contains("载货"))
				return VehicleBonusType.PT;
			else
				return VehicleBonusType.OTHER;
		} else 
			return VehicleBonusType.OTHER;
	}
	
	/**
	 * 根据简捷返回的转续保状态字段获取保途的转续保枚举类型
	 * 
	 * @return
	 */
	public static final PolicyNature natureFromJianJie(String baseStatus) {
		if (baseStatus.equals("新保"))
			return PolicyNature.NEW;
		else if (baseStatus.equals("转保"))
			return PolicyNature.RE_INSURANCE;
		else if (baseStatus.equals("续保"))
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
	public static final Map<CommercialInsuranceType, org.btkj.pojo.model.Insurance> jianJieInsuranceMapping(List<Insurance> insurances) {
		Map<CommercialInsuranceType, org.btkj.pojo.model.Insurance> map = new HashMap<CommercialInsuranceType, org.btkj.pojo.model.Insurance>();
		for (Insurance temp : insurances) {
			String name = temp.getName();
			if (name.contains("三者")) {
				if (_isDeductible(name))
					map.put(CommercialInsuranceType.THIRD_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				else {
					map.put(CommercialInsuranceType.THIRD, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
					if (null != temp.getMpBf() && Double.valueOf(temp.getMpBf()) != 0) 
						map.put(CommercialInsuranceType.THIRD_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				}
			} else if (name.contains("车辆损失") || name.contains("车损失") && !name.contains("三方") && !name.contains("特约")) {
				if (_isDeductible(name))
					map.put(CommercialInsuranceType.DAMAGE_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				else {
					map.put(CommercialInsuranceType.DAMAGE, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
					if (null != temp.getMpBf() && Double.valueOf(temp.getMpBf()) != 0) 
						map.put(CommercialInsuranceType.DAMAGE_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				}
			} else if (name.contains("司机") || name.contains("驾驶员") || name.contains("驾驶人")) {
				if (_isDeductible(name))
					map.put(CommercialInsuranceType.DRIVER_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				else {
					map.put(CommercialInsuranceType.DRIVER, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
					if (null != temp.getMpBf() && Double.valueOf(temp.getMpBf()) != 0) 
						map.put(CommercialInsuranceType.DRIVER_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				}
			} else if (name.contains("乘客")) {
				if (_isDeductible(name))
					map.put(CommercialInsuranceType.PASSENGER_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				else {
					map.put(CommercialInsuranceType.PASSENGER, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
					if (null != temp.getMpBf() && Double.valueOf(temp.getMpBf()) != 0) 
						map.put(CommercialInsuranceType.PASSENGER_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				}
			} else if (name.contains("盗抢")) {
				if (_isDeductible(name))
					map.put(CommercialInsuranceType.ROBBERY_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				else {
					map.put(CommercialInsuranceType.ROBBERY, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
					if (null != temp.getMpBf() && Double.valueOf(temp.getMpBf()) != 0) 
						map.put(CommercialInsuranceType.ROBBERY_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				}
			} else if (name.contains("玻璃")) 
				map.put(CommercialInsuranceType.GLASS, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
			else if (name.contains("自燃")) {
				if (_isDeductible(name))
					map.put(CommercialInsuranceType.AUTO_FIRE_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				else {
					map.put(CommercialInsuranceType.AUTO_FIRE, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
					if (null != temp.getMpBf() && Double.valueOf(temp.getMpBf()) != 0) 
						map.put(CommercialInsuranceType.AUTO_FIRE_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				}
			} else if (name.contains("划痕")) {
				if (_isDeductible(name))
					map.put(CommercialInsuranceType.SCRATCH_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				else {
					map.put(CommercialInsuranceType.SCRATCH, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
					if (null != temp.getMpBf() && Double.valueOf(temp.getMpBf()) != 0) 
						map.put(CommercialInsuranceType.SCRATCH_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				}
			} else if (name.contains("无法")) 
				map.put(CommercialInsuranceType.UNKNOWN_THIRD, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
			else if (name.contains("涉水")) {
				if (_isDeductible(name))
					map.put(CommercialInsuranceType.WADDING_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				else {
					map.put(CommercialInsuranceType.WADDING, new org.btkj.pojo.model.Insurance(temp.getBe(), temp.getBf()));
					if (null != temp.getMpBf() && Double.valueOf(temp.getMpBf()) != 0) 
						map.put(CommercialInsuranceType.WADDING_DEDUCTIBLE, new org.btkj.pojo.model.Insurance("1", temp.getBf()));
				}
			}
		}
		return map;
	}
	
	private static boolean _isDeductible(String name) {
		return name.contains("不计") || name.contains("免赔") || name.contains("不计免赔");
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
	
	public static final IDType idTypeFromBiHuIdType(int biHuIdType) {
		switch (biHuIdType) {
		case 1:
			return IDType.IDENTITY;
		case 2:
			return IDType.ORGANIZATION_LICENSE;
		case 3:
			return IDType.PASSPORT;
		case 4:
			return IDType.MILITARY_CARD;
		case 5:
			return IDType.RE_ENTRY_PERMIT;
		case 0:
		case 6:
		case 8:
			return IDType.OTHER;
		case 7:
			return IDType.EEP_HK_MACAU;
		case 9:
			return IDType.BUSINESS_LICENSE;
		case 10:
			return IDType.TAX_REGISTRATION_CERTIFICATE;
		default:
			throw new IllegalArgumentException("unknown bihu id type " + biHuIdType);
		}
	}
	
	public static final VehicleUsedType vehicleUsedTypeFromBiHuUsedType(int biHuUsedType) {
		switch (biHuUsedType) {
		case 1:
			return VehicleUsedType.HOME_USE;
		case 2:
			return VehicleUsedType.ORGAN;
		case 3:
			return VehicleUsedType.ENTERPRISE;
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
	
	public static final Mod employeeModFromVehicleUsedType(VehicleUsedType usedType) {
		switch (usedType) {
		case HOME_USE:
		case ENTERPRISE:
		case ORGAN:
			return Mod.BONUS_NPC;
		case LEASE:
		case CITY_BUS:
		case HIGHWAY_TRANSPORT:
			return Mod.BONUS_PC;
		case BIZ_TRUCK:
			return Mod.BONUS_PT;
		case NO_BIZ_TRUCK:
			return Mod.BONUS_NPT;
		default:
			return Mod.BONUS_OTHER;
		}
	}
	
	/**
	 * 获取车龄
	 * 
	 * @param order
	 * @return
	 */
	public static final int vehicleAge(String enrollDate, String commercialStart) {
		long timestamp = DateUtil.getTimeGap(commercialStart, enrollDate, DateUtil.YYYY_MM_DD_HH_MM_SS, TimeZone.getDefault());
		return timestamp <= 0 ? 0 : (int) (timestamp / (365 * 24 * 3600 * 1000));
	}
	
	/**
	 * 是否是新车，新车没有车牌
	 * 
	 * @return
	 */
	public static final boolean isNewVehicleLicense(String license) {
		return false;
	}
	
	public static final String totalPremium(VehicleOrder order) {
		VehiclePolicyTips tips = order.getTips();
		if (null == tips)
			return "0";
		PolicySchema schema = tips.getSchema();
		if (null == schema)
			return "0";
		BigDecimal total = new BigDecimal(null == schema.getCommercialTotal() ? "0" : schema.getCommercialTotal())
				.add(new BigDecimal(null == schema.getCompulsoryTotal() ? "0" : schema.getCompulsoryTotal()))
				.add(new BigDecimal(null == schema.getVehicleVesselTotal() ? "0" : schema.getVehicleVesselTotal()));
		return total.setScale(2, RoundingMode.HALF_UP).toString();
	}
	
	/**
	 * 获取以分为单位的保费
	 * 
	 * @param order
	 * @return
	 */
	public static final int getTotalQuotaInCent(VehicleOrder order) {
		String premuim = totalPremium(order);
		return new BigDecimal(premuim).multiply(new BigDecimal(100)).intValue();
	}
	
	/**
	 * 获取雇员关系路径上的团队成员
	 * 
	 * @param relationPath
	 * @param teamDepth
	 * @return
	 */
	public static LinkedList<Integer> relationEmployees(String relationPath, int teamDepth) {
		teamDepth --;
		LinkedList<Integer> list = CollectionUtil.toIntLinkedList(relationPath.split(Consts.SYMBOL_UNDERLINE));
		while (list.size() > teamDepth)
			list.poll();
		return list;
	}
}
