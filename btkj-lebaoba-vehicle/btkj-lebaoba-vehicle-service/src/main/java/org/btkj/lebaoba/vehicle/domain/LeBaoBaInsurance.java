package org.btkj.lebaoba.vehicle.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.btkj.lebaoba.vehicle.domain.QuoteResult.AmountItem;
import org.btkj.lebaoba.vehicle.domain.QuoteSubmit.VehicleInsuranceItem;
import org.btkj.pojo.enums.CommercialInsuranceType;
import org.btkj.pojo.model.Insurance;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.pojo.param.VehicleOrderParam.InsuranceItem;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;

public enum LeBaoBaInsurance {

	Z1 {				// 车损险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.DAMAGE, new Insurance(item.getAmount(), item.getPremium()));
		}				
	},	
	B1 {				// 车损险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.DAMAGE_DEDUCTIBLE, new Insurance("1", item.getPremium()));
		}
	},
	Z2 {				// 盗抢险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.ROBBERY, new Insurance(item.getAmount(), item.getPremium()));
		}
	},	
	B2 {				// 盗抢险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.ROBBERY_DEDUCTIBLE, new Insurance("1", item.getPremium()));
		}
	},	
	Z3 {				// 三者险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.THIRD, new Insurance(item.getAmount(), item.getPremium()));
		}
	},		
	B3 {				// 三者险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.THIRD_DEDUCTIBLE, new Insurance("1", item.getPremium()));
		}
	},	
	Z4 {				// 座位险(司机)
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.DRIVER, new Insurance(item.getAmount(), item.getPremium()));
		}
	},	
	B4 {				// 座位险(司机)不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.DRIVER_DEDUCTIBLE, new Insurance("1", item.getPremium()));
		}
	},
	Z5 {				// 座位险(乘客)
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.PASSENGER, new Insurance(item.getAmount(), item.getPremium()));
		}
	},					
	B5 {				// 座位险(乘客)不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.PASSENGER_DEDUCTIBLE, new Insurance("1", item.getPremium()));
		}
	},					
	B6 {				// 座位险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {}
	},	
	F1 {				// 划痕险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.SCRATCH, new Insurance(item.getAmount(), item.getPremium()));
		}
	},
	B7 {				// 划痕险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.SCRATCH_DEDUCTIBLE, new Insurance("1", item.getPremium()));
		}
	},		
	F5 {				// 自燃险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.AUTO_FIRE, new Insurance(item.getAmount(), item.getPremium()));
		}
	},	
	B8 {				// 自燃险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.AUTO_FIRE, new Insurance("1", item.getPremium()));
		}
	},					
	B9 {				// 附加险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {}
	},		
	F8 {				// 涉水发动机损坏险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.WADDING, new Insurance("1", item.getPremium()));
		}
	},	
	B11 {				// 涉水发动机损坏险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.WADDING_DEDUCTIBLE, new Insurance("1", item.getPremium()));
		}
	},				
	B12 {				// 主险不计免赔
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {}
	},				
	F2 {				// 破碎险
		@Override	
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.GLASS, new Insurance(item.getAmount().equals("10") ? "1" : "2", item.getPremium()));
		}
	},					
	F12 {				// 机动车损失保险无法找到第三方特约险
		@Override
		public void insuranceMapping(Map<CommercialInsuranceType, Insurance> insurances, AmountItem item) {
			insurances.put(CommercialInsuranceType.UNKNOWN_THIRD, new Insurance("1", item.getPremium()));
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
	
	public static final List<VehicleInsuranceItem> insuranceMapping(VehicleOrderParam param, String cmstart, String enrollDate) {
		List<VehicleInsuranceItem> items = new ArrayList<VehicleInsuranceItem>();
		Map<CommercialInsuranceType, InsuranceItem> insurances = param.getInsurances();
		if (!CollectionUtil.isEmpty(insurances)) {
			for (Entry<CommercialInsuranceType, InsuranceItem> entry : insurances.entrySet()) {
				switch (entry.getKey()) {
				case DAMAGE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.Z1, _calculateDepreciationPrice(String.valueOf(param.getPrice()), "0.006", cmstart, enrollDate)));
					break;
				case DAMAGE_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B1));
					break;
				case THIRD:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.Z3, String.valueOf(entry.getValue().getQuota())));
					break;
				case THIRD_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B3));
					break;
				case DRIVER:
					VehicleInsuranceItem insuranceItem = new VehicleInsuranceItem(LeBaoBaInsurance.Z4);
					insuranceItem.setQuantity(1);
					insuranceItem.setAmount(String.valueOf(entry.getValue().getQuota()));
					insuranceItem.setUnitAmount(String.valueOf(entry.getValue().getQuota()));
					items.add(insuranceItem);
					break;
				case DRIVER_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B4));
					break;
				case PASSENGER:
					insuranceItem = new VehicleInsuranceItem(LeBaoBaInsurance.Z5);
					insuranceItem.setQuantity(param.getSeat() - 1);
					insuranceItem.setUnitAmount(String.valueOf(entry.getValue().getQuota()));
					BigDecimal bigDecimal = new BigDecimal(entry.getValue().getQuota());
					insuranceItem.setAmount(bigDecimal.multiply(new BigDecimal(param.getSeat() - 1)).toString());
					items.add(insuranceItem);
					break;
				case PASSENGER_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B5));
					break;
				case ROBBERY:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.Z2, _calculateDepreciationPrice(String.valueOf(param.getPrice()), "0.006", cmstart, enrollDate)));
					break;
				case ROBBERY_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B2));
					break;
				case GLASS:
					if (entry.getValue().getQuota().equals("1"))
						items.add(new VehicleInsuranceItem(LeBaoBaInsurance.F2, "10"));
					else if (entry.getValue().getQuota().equals("2"))
						items.add(new VehicleInsuranceItem(LeBaoBaInsurance.F2, "20"));
					break;
				case AUTO_FIRE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.F5, _calculateDepreciationPrice(String.valueOf(param.getPrice()), "0.006", cmstart, enrollDate)));
					break;
				case AUTO_FIRE_DEDUCTIBLE:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.B8));
					break;
				case SCRATCH:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.F1, String.valueOf(entry.getValue().getPrice())));
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
				case UNKNOWN_THIRD:
					items.add(new VehicleInsuranceItem(LeBaoBaInsurance.F12));
					break;
				default:
					break;
				}
			}
		}
		if (StringUtil.hasText(param.getCompulsoryStart())) {
			items.add(new VehicleInsuranceItem(LeBaoBaInsurance.J1, "122000"));
			items.add(new VehicleInsuranceItem(LeBaoBaInsurance.CCS, "1"));
		}
		return items;
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
		return price.compareTo(cprice) < 0 ? cprice.setScale(2, RoundingMode.HALF_UP).toString() : price.setScale(2, RoundingMode.HALF_UP).toString();
	}
}
