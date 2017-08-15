package org.btkj.lebaoba.vehicle.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.lebaoba.vehicle.domain.OrderResult.AmountItem;
import org.btkj.lebaoba.vehicle.domain.OrderSubmit.VehicleInsuranceItem;
import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;

public enum LeBaoBaInsurance {

	Z1 {				// 车损险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.DAMAGE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}				
	},	
	B1 {				// 车损险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.DAMAGE_DEDUCTIBLE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},
	Z2 {				// 盗抢险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.ROBBERY, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},	
	B2 {				// 盗抢险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.ROBBERY_DEDUCTIBLE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},	
	Z3 {				// 三者险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.THIRD, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},		
	B3 {				// 三者险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.THIRD_DEDUCTIBLE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},	
	Z4 {				// 座位险(司机)
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.DRIVER, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},	
	B4 {				// 座位险(司机)不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.DRIVER_DEDUCTIBLE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},
	Z5 {				// 座位险(乘客)
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.PASSENGER, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},					
	B5 {				// 座位险(乘客)不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.PASSENGER_DEDUCTIBLE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},					
	B6 {				// 座位险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {}
	},	
	F1 {				// 划痕险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.SCRATCH, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},
	B7 {				// 划痕险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.SCRATCH_DEDUCTIBLE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},		
	F5 {				// 自燃险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.AUTO_FIRE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},	
	B8 {				// 自燃险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.AUTO_FIRE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},					
	B9 {				// 附加险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {}
	},		
	F8 {				// 涉水发动机损坏险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.WADDING, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},	
	B11 {				// 涉水发动机损坏险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.WADDING_DEDUCTIBLE, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},				
	B12 {				// 主险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {}
	},				
	F2 {				// 破碎险
		@Override	
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.GLASS, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},					
	F3 {				// 指定专修厂特约条款
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.GARAGE_DESIGNATED, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},					
	F12 {				// 机动车损失保险无法找到第三方特约险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.UNKNOWN_THIRD, new Insurance(Double.valueOf(item.getAmount()), Double.valueOf(item.getPremium())));
		}
	},				
	J1 {				// 交强险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {}
	},					
	CCS {				// 车船税
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {}
	};				
	
	/**
	 * 将乐保吧的险种映射为保途的险种
	 * 
	 * @param insurances
	 */
	public abstract void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item);
	
	public static final LeBaoBaInsurance match(String code) {
		for (LeBaoBaInsurance temp : LeBaoBaInsurance.values()) {
			if (temp.name().equals(code))
				return temp;
		}
		return null;
	}
	
	public static final List<VehicleInsuranceItem> insuranceMapping(PolicySchema schema) {
		List<VehicleInsuranceItem> items = new ArrayList<VehicleInsuranceItem>();
		Map<CommercialInsuranceType, Insurance> insurances = schema.getInsurances();
		if (!CollectionUtil.isEmpty(insurances)) {
			for (Entry<CommercialInsuranceType, Insurance> entry : insurances.entrySet()) {
				switch (entry.getKey()) {
				case DAMAGE:
					break;
				case DAMAGE_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B1));
					break;
				case THIRD:
					break;
				case THIRD_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B3));
					break;
				case DRIVER:
					break;
				case DRIVER_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B4));
					break;
				case PASSENGER:
					break;
				case PASSENGER_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B5));
					break;
				case ROBBERY:
					break;
				case ROBBERY_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B2));
					break;
				case GLASS:
					break;
				case AUTO_FIRE:
					break;
				case AUTO_FIRE_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B8));
					break;
				case SCRATCH:
					// TODO:
					break;
				case SCRATCH_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B7));
					break;
				case WADDING:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.F8));
					break;
				case WADDING_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B11));
					break;
				case GARAGE_DESIGNATED:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.F3));
					break;
				case UNKNOWN_THIRD:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.F12));
					break;
				default:
					break;
				}
			}
		}
		if (StringUtil.hasText(schema.getCompulsiveStart())) {
			items.add(new VehicleInsuranceItem(LeBaoBaInsurance.J1, "122000"));
			items.add(new VehicleInsuranceItem(LeBaoBaInsurance.CCS, "1"));
		}
		return null;
	}
	
	private static int _diffMonth(String cmTime, String enrollTime) {
		GregorianCalendar cmDate = new GregorianCalendar(DateUtil.TIMEZONE_GMT_8);
		cmDate.setTimeInMillis(DateUtil.getTime(cmTime, DateUtil.YYYY_MM_DDTHH_MM_SS));
		GregorianCalendar enrollDate = new GregorianCalendar(DateUtil.TIMEZONE_GMT_8);
		enrollDate.setTimeInMillis(DateUtil.getTime(enrollTime, DateUtil.YYYY_MM_DDTHH_MM_SS));
		int month = Math.abs(cmDate.get(Calendar.YEAR) - enrollDate.get(Calendar.YEAR)) * 12;
		month += cmDate.get(Calendar.MONTH) - enrollDate.get(Calendar.MONTH);
		if (cmDate.get(Calendar.DAY_OF_MONTH) < enrollDate.get(Calendar.DAY_OF_MONTH))
			month --;
		return month;
	}
	
	/**
	 * 盗抢险、车损险、自燃险的计算方法
	 * 
	 * @param purchasePrice
	 * @param depreciationRate
	 * @param cmTime
	 * @param enrollTime
	 * @return
	 */
	private static String _calculateDepreciationPrice(String purchasePrice, String depreciationRate, String cmTime, String enrollTime) {
		BigDecimal rate = new BigDecimal(_diffMonth(cmTime, enrollTime)).multiply(new BigDecimal(depreciationRate));
		BigDecimal price = new BigDecimal(purchasePrice).multiply(new BigDecimal(1).subtract(rate));
		BigDecimal cprice = new BigDecimal(purchasePrice).multiply(new BigDecimal("0.2"));
		return price.compareTo(cprice) > 0 ? price.toString() : price.toString();
	}
}
