package org.btkj.config.persistence;

public enum Table {
	
	APP("app"),
	INSURANCE("insurance"),
	REGION_CITY("region_city"),
	REGION_DISTRICT("region_district"),
	REGION_PROVINCE("region_province");

	private String key;
	
	private Table(String key) {
		this.key = key;
	}
	
	public String key() {
		return key;
	}
}
