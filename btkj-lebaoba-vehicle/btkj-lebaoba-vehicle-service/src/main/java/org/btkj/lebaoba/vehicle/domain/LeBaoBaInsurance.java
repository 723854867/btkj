package org.btkj.lebaoba.vehicle.domain;

import java.util.Map;

import org.btkj.lebaoba.vehicle.domain.OrderResult.AmountItem;
import org.btkj.pojo.bo.Insurance;
import org.btkj.pojo.enums.CommercialInsuranceType;

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
}
