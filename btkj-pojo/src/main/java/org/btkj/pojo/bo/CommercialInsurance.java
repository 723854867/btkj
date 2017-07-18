package org.btkj.pojo.bo;

import java.io.Serializable;

/**
 * 商业险
 * 
 * @author ahab
 */
public class CommercialInsurance implements Serializable {

	private static final long serialVersionUID = 519650193872414878L;

	private String start;						// 起保日期
	private String end;							// 截止日期
	private double total;						// 商业险总额
	private double noLossDiscountRate;			// 无赔款优惠系数
	private double autoChannelRate;				// 自主渠道系数
	private double autoUnderwritingRate;		// 自主核保系数
	private double trafficViolationRate;		// 交通违法系数
	private Insurance damage;					// 车损险
	private Insurance damageOfDeductible;		// 车损不计免赔
	private Insurance third;					// 第三方责任险
	private Insurance thirdOfDeductible;		// 第三方不计免赔
	private Insurance driver;					// 车上人员责任险(司机)
	private Insurance driverOfDeductible;		// 车上人员责任险(司机)不计免赔
	private Insurance passenger;				// 车上人员责任险(乘客)
	private Insurance passengerOfDeductible;	// 车上人员责任险(乘客)不计免赔
	private Insurance robbery;					// 盗抢险
	private Insurance robberyOfDeductible;		// 盗抢险不计免赔
	private Insurance glass;					// 玻璃破碎险
	private Insurance autoFire;					// 自燃险
	private Insurance autoFireOfDeductible;		// 自燃险不计免赔
	private Insurance scratch;					// 划痕险
	private Insurance scratchOfDeductible;		// 划痕险不计免赔
	private Insurance wading;					// 涉水险
	private Insurance wadingOfDeductible;		// 涉水险不计免赔
	private Insurance garageDesignated;			// 指定专修厂
	private Insurance unknownThird;				// 无法找到第三方特约险
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getNoLossDiscountRate() {
		return noLossDiscountRate;
	}

	public void setNoLossDiscountRate(double noLossDiscountRate) {
		this.noLossDiscountRate = noLossDiscountRate;
	}

	public double getAutoChannelRate() {
		return autoChannelRate;
	}

	public void setAutoChannelRate(double autoChannelRate) {
		this.autoChannelRate = autoChannelRate;
	}

	public double getAutoUnderwritingRate() {
		return autoUnderwritingRate;
	}

	public void setAutoUnderwritingRate(double autoUnderwritingRate) {
		this.autoUnderwritingRate = autoUnderwritingRate;
	}

	public double getTrafficViolationRate() {
		return trafficViolationRate;
	}

	public void setTrafficViolationRate(double trafficViolationRate) {
		this.trafficViolationRate = trafficViolationRate;
	}

	public Insurance getDamage() {
		return damage;
	}

	public void setDamage(Insurance damage) {
		this.damage = damage;
	}

	public Insurance getDamageOfDeductible() {
		return damageOfDeductible;
	}

	public void setDamageOfDeductible(Insurance damageOfDeductible) {
		this.damageOfDeductible = damageOfDeductible;
	}

	public Insurance getThird() {
		return third;
	}

	public void setThird(Insurance third) {
		this.third = third;
	}

	public Insurance getThirdOfDeductible() {
		return thirdOfDeductible;
	}

	public void setThirdOfDeductible(Insurance thirdOfDeductible) {
		this.thirdOfDeductible = thirdOfDeductible;
	}

	public Insurance getDriver() {
		return driver;
	}

	public void setDriver(Insurance driver) {
		this.driver = driver;
	}

	public Insurance getDriverOfDeductible() {
		return driverOfDeductible;
	}

	public void setDriverOfDeductible(Insurance driverOfDeductible) {
		this.driverOfDeductible = driverOfDeductible;
	}

	public Insurance getPassenger() {
		return passenger;
	}

	public void setPassenger(Insurance passenger) {
		this.passenger = passenger;
	}

	public Insurance getPassengerOfDeductible() {
		return passengerOfDeductible;
	}

	public void setPassengerOfDeductible(Insurance passengerOfDeductible) {
		this.passengerOfDeductible = passengerOfDeductible;
	}

	public Insurance getRobbery() {
		return robbery;
	}

	public void setRobbery(Insurance robbery) {
		this.robbery = robbery;
	}

	public Insurance getRobberyOfDeductible() {
		return robberyOfDeductible;
	}

	public void setRobberyOfDeductible(Insurance robberyOfDeductible) {
		this.robberyOfDeductible = robberyOfDeductible;
	}

	public Insurance getGlass() {
		return glass;
	}

	public void setGlass(Insurance glass) {
		this.glass = glass;
	}

	public Insurance getAutoFire() {
		return autoFire;
	}

	public void setAutoFire(Insurance autoFire) {
		this.autoFire = autoFire;
	}

	public Insurance getAutoFireOfDeductible() {
		return autoFireOfDeductible;
	}

	public void setAutoFireOfDeductible(Insurance autoFireOfDeductible) {
		this.autoFireOfDeductible = autoFireOfDeductible;
	}

	public Insurance getScratch() {
		return scratch;
	}

	public void setScratch(Insurance scratch) {
		this.scratch = scratch;
	}

	public Insurance getScratchOfDeductible() {
		return scratchOfDeductible;
	}

	public void setScratchOfDeductible(Insurance scratchOfDeductible) {
		this.scratchOfDeductible = scratchOfDeductible;
	}
	
	public Insurance getWading() {
		return wading;
	}
	
	public void setWading(Insurance wading) {
		this.wading = wading;
	}
	
	public Insurance getWadingOfDeductible() {
		return wadingOfDeductible;
	}
	
	public void setWadingOfDeductible(Insurance wadingOfDeductible) {
		this.wadingOfDeductible = wadingOfDeductible;
	}

	public Insurance getGarageDesignated() {
		return garageDesignated;
	}

	public void setGarageDesignated(Insurance garageDesignated) {
		this.garageDesignated = garageDesignated;
	}

	public Insurance getUnknownThird() {
		return unknownThird;
	}

	public void setUnknownThird(Insurance unknownThird) {
		this.unknownThird = unknownThird;
	}
}
