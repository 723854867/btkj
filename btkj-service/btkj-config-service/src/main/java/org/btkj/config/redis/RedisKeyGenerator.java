package org.btkj.config.redis;

import java.text.MessageFormat;

import org.btkj.config.persistence.Table;

public class RedisKeyGenerator {

	private static final String APP_DATA				= "hash:db:app";
	private static final String INSURANCE_DATA			= "hash:db:insurance";
	private static final String REGION_CITY_DATA		= "hash:db:region_city";
	private static final String REGION_PROVINCE_DATA	= "hash:db:region_province";
	private static final String REGION_DISTRICT_DATA	= "hash:db:region_district";
	
	private static final String TABLE_LOCK				= "string:tmp:lock:table:{0}";		// 表锁
	
	public final static String appDataKey() { 
		return APP_DATA;
	}
	
	public final static String insuranceDataKey() {
		return INSURANCE_DATA;
	}
	
	public final static String regionCityDataKey() {
		return REGION_CITY_DATA;
	}
	
	public final static String regionDistrictDataKey() { 
		return REGION_DISTRICT_DATA;
	}
	
	public final static String regionProvinceDataKey() {
		return REGION_PROVINCE_DATA;
	}
	
	public final static String tableLockKey(Table table) {
		return MessageFormat.format(TABLE_LOCK, table.key());
	}
}
