package org.btkj.config.redis;

public class RedisKeyGenerator {

	private static final String APP_DATA				= "hash:db:app";
	private static final String REGION_DATA				= "hash:db:region";
	private static final String INSURANCE_DATA			= "hash:db:insurance";
	private static final String NON_AUTO_INSURANCE		= "hash:db:non_auto_insurance";
	
	public static final String appDataKey() { 
		return APP_DATA;
	}
	
	public static final String insuranceDataKey() {
		return INSURANCE_DATA;
	}
	
	public static final String regionDataKey() {
		return REGION_DATA;
	}
	
	public static final String nonAutoInsuranceDataKey() {
		return NON_AUTO_INSURANCE;
	}
}
