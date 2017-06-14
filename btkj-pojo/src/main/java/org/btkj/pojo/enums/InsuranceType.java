package org.btkj.pojo.enums;

public enum InsuranceType {

	/**
	 * 车损
	 */
	DAMAGE(1, true),
	
	/**
	 * 车损不计免赔
	 */
	DAMAGE_DEDUCTIBLE(1 << 1, false) {
		@Override
		public int need() {
			return InsuranceType.DAMAGE.mark();
		}
	},
	
	/**
	 * 第三者
	 */
	THIRD(1 << 2, true),
	
	/**
	 * 第三者不计免赔
	 */
	THIRD_DEDUCTIBLE(1 << 3, false) {
		@Override
		public int need() {
			return InsuranceType.THIRD.mark();
		}
	},
	
	/**
	 * 车上人员司机
	 */
	DRIVER(1 << 4, true),
	
	/**
	 * 司机不计免赔
	 */
	DRIVER_DEDUCTIBLE(1 << 5, false) {
		@Override
		public int need() {
			return InsuranceType.DRIVER.mark();
		}
	},
	
	/**
	 * 车上人员乘客
	 */
	PASSENGER(1 << 6, true),
	
	/**
	 * 乘客不计免赔
	 */
	PASSENGER_DEDUCTIBLE(1 << 7, false) {
		@Override
		public int need() {
			return InsuranceType.PASSENGER.mark();
		}
	},
	
	/**
	 * 盗抢
	 */
	ROBBERY(1 << 8, true),
	
	/**
	 * 盗抢不计免赔
	 */
	ROBBERY_DEDUCTIBLE(1 << 9, false) {
		@Override
		public int need() {
			return InsuranceType.ROBBERY.mark();
		}
	},
	
	/**
	 * 玻璃破碎
	 */
	GLASS(1 << 10, false),
	
	/**
	 * 自燃
	 */
	AUTO_FIRE(1 << 11, false),
	
	/**
	 * 自然不计免赔
	 */
	AUTO_FIRE_DEDUCTIBLE(1 << 12, false) {
		@Override
		public int need() {
			return InsuranceType.AUTO_FIRE.mark();
		}
	},
	
	/**
	 * 划痕
	 */
	SCRATCH(1 << 13, false),
	
	/**
	 * 划痕不计免赔
	 */
	SCRATCH_DEDUCTIBLE(1 << 14, false) {
		@Override
		public int need() {
			return InsuranceType.SCRATCH.mark();
		}
	},
	
	/**
	 * 涉水
	 */
	WADDING(1 << 15, false),
	
	/**
	 * 涉水不计免赔
	 */
	WADDING_DEDUCTIBLE(1 << 16, false) {
		@Override
		public int need() {
			return InsuranceType.WADDING.mark();
		}
	},
	
	/**
	 * 指定修理厂
	 */
	GARAGE_DESIGNATED(1 << 17, false),
	
	/**
	 * 无法找到第三方
	 */
	UNKNOWN_THIRD(1 << 18, false);
	
	private int mark;
	private boolean basic;
	
	private InsuranceType(int mark, boolean basic) {
		this.mark = mark;
		this.basic = basic;
	}
	
	public int mark() {
		return mark;
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
