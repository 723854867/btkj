package org.btkj.statistics.redis;

public class KeyGenerator {

	private static final String VEHICLE_QUOTE_RECORD = "hash:memory:vqr";
	
	public static final String vehiclQuoteRecordKey() {
		return VEHICLE_QUOTE_RECORD;
	}
}
