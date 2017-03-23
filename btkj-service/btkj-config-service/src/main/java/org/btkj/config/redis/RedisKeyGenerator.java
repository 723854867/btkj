package org.btkj.config.redis;

public class RedisKeyGenerator {

	private static final String APP_DATA				= "hash:db:app";
	private static final String INSURANCE_DATA			= "hash:db:insurance";
	private static final String REGION_CITY_DATA		= "hash:db:region_city";
	private static final String REGION_PROVINCE_DATA	= "hash:db:region_province";
	private static final String REGION_DISTRICT_DATA	= "hash:db:region_district";
	private static final String NON_AUTO_INSURANCE		= "hash:db:non_auto_insurance";
	
	public static final String appDataKey() { 
		return APP_DATA;
	}
	
	public static final String insuranceDataKey() {
		return INSURANCE_DATA;
	}
	
	public static final String regionCityDataKey() {
		return REGION_CITY_DATA;
	}
	
	public static final String regionDistrictDataKey() { 
		return REGION_DISTRICT_DATA;
	}
	
	public static final String regionProvinceDataKey() {
		return REGION_PROVINCE_DATA;
	}
	
	public static final String nonAutoInsuranceDataKey() {
		return NON_AUTO_INSURANCE;
	}
}
