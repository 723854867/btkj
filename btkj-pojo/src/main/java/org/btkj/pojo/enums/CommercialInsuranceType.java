package org.btkj.pojo.enums;

/**
 * 商业险类型
 * 
 * @author ahab
 */
public enum CommercialInsuranceType {

	/**
	 * 车损
	 */
	DAMAGE(1, "车损险", true),
	
	/**
	 * 车损不计免赔
	 */
	DAMAGE_DEDUCTIBLE(1 << 1, "车损不计免赔", false) {
		@Override
		public int need() {
			return CommercialInsuranceType.DAMAGE.mark();
		}
	},
	
	/**
	 * 第三者
	 */
	THIRD(1 << 2, "三者险", true),
	
	/**
	 * 第三者不计免赔
	 */
	THIRD_DEDUCTIBLE(1 << 3, "三者不计免赔", false) {
		@Override
		public int need() {
			return CommercialInsuranceType.THIRD.mark();
		}
	},
	
	/**
	 * 车上人员司机
	 */
	DRIVER(1 << 4, "司机险", true),
	
	/**
	 * 司机不计免赔
	 */
	DRIVER_DEDUCTIBLE(1 << 5, "司机不计免赔", false) {
		@Override
		public int need() {
			return CommercialInsuranceType.DRIVER.mark();
		}
	},
	
	/**
	 * 车上人员乘客
	 */
	PASSENGER(1 << 6, "乘客险", true),
	
	/**
	 * 乘客不计免赔
	 */
	PASSENGER_DEDUCTIBLE(1 << 7, "乘客不计免赔", false) {
		@Override
		public int need() {
			return CommercialInsuranceType.PASSENGER.mark();
		}
	},
	
	/**
	 * 盗抢
	 */
	ROBBERY(1 << 8, "盗抢险", true),
	
	/**
	 * 盗抢不计免赔
	 */
	ROBBERY_DEDUCTIBLE(1 << 9, "盗抢不计免赔", false) {
		@Override
		public int need() {
			return CommercialInsuranceType.ROBBERY.mark();
		}
	},
	
	/**
	 * 玻璃破碎
	 */
	GLASS(1 << 10, "玻璃险", false),
	
	/**
	 * 自燃
	 */
	AUTO_FIRE(1 << 11, "自燃险", false),
	
	/**
	 * 自然不计免赔
	 */
	AUTO_FIRE_DEDUCTIBLE(1 << 12, "自燃不计免赔", false) {
		@Override
		public int need() {
			return CommercialInsuranceType.AUTO_FIRE.mark();
		}
	},
	
	/**
	 * 划痕
	 */
	SCRATCH(1 << 13, "划痕险", false),
	
	/**
	 * 划痕不计免赔
	 */
	SCRATCH_DEDUCTIBLE(1 << 14, "划痕不计免赔", false) {
		@Override
		public int need() {
			return CommercialInsuranceType.SCRATCH.mark();
		}
	},
	
	/**
	 * 涉水
	 */
	WADDING(1 << 15, "涉水险", false),
	
	/**
	 * 涉水不计免赔
	 */
	WADDING_DEDUCTIBLE(1 << 16, "涉水不计免赔", false) {
		@Override
		public int need() {
			return CommercialInsuranceType.WADDING.mark();
		}
	},
	
	/**
	 * 无法找到第三方
	 */
	UNKNOWN_THIRD(1 << 18, "第三方特约险", false);
	
	private int mark;
	private String title;
	private boolean basic;
	
	private CommercialInsuranceType(int mark, String title, boolean basic) {
		this.mark = mark;
		this.title = title;
		this.basic = basic;
	}
	
	public int mark() {
		return mark;
	}
	
	public String title() {
		return title;
	}
	
	public boolean isBasic() { 
		return this.basic;
	}
	
	/**
	 * 如果是基本险则不需要任何其他险，如果是附加险必须要求有车损险
	 * @return
	 */
	public int need() {
		if (isBasic())
			return 0;
		else
			return DAMAGE.mark;
	}
}
