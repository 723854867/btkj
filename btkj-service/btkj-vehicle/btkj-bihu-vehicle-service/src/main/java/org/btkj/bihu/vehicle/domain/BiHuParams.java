package org.btkj.bihu.vehicle.domain;

import java.util.TreeMap;

public class BiHuParams extends TreeMap<String, String> {

	private static final long serialVersionUID = -143173514303834180L;

	protected static final String KEY						= "key";
	protected static final String AGENT						= "Agent";
	protected static final String GROUP						= "Group";
	protected static final String CITY_CODE					= "CityCode";
	protected static final String LICENSE_NO				= "LicenseNo";
	protected static final String CAN_SHOW_NO				= "CanShowNo";
	protected static final String CAN_SHOW_EXHAUST_SCALE	= "CanShowExhaustScale";
	protected static final String SHOW_XIU_LI_CHANG_TYPE	= "ShowXiuLiChangType";
	protected static final String TIME_FORMAT				= "TimeFormat";
	protected static final String SHOW_AUTO_MOLD_CODE		= "ShowAutoMoldCode";
	protected static final String CUST_KEY					= "CustKey";
	
	private String key;
	
	public BiHuParams() {
		setGroup(1);
		setCanShowNo(1);
		setCanShowExhaustScale(1);
		setShowXiuLiChangeType(1);
		setTimeFormat(1);
	}
	
	public String getKey() {
		return key;
	}
	
	public BiHuParams setKey(String key) {
		this.key = key;
		return this;
	}
	
	public BiHuParams setAgent(String agent) {
		put(AGENT, agent);
		return this;
	}
	
	public BiHuParams setGroup(int group) {
		put(GROUP, String.valueOf(group));
		return this;
	}
	
	public BiHuParams setCityCode(int cityCode) {
		put(CITY_CODE, String.valueOf(cityCode));
		return this;
	}
	
	public BiHuParams setLicenseNo(String licenseNo) {
		put(LICENSE_NO, licenseNo);
		return this;
	}
	
	public BiHuParams setCanShowNo(int canShowNo) {
		put(CAN_SHOW_NO, String.valueOf(canShowNo));
		return this;
	}
	
	public BiHuParams setCanShowExhaustScale(int canShowExhaustScale) {
		put(CAN_SHOW_EXHAUST_SCALE, String.valueOf(canShowExhaustScale));
		return this;
	}
	
	public BiHuParams setShowXiuLiChangeType(int showXiuLiChangType) {
		put(SHOW_XIU_LI_CHANG_TYPE, String.valueOf(showXiuLiChangType));
		return this;
	}
	
	public BiHuParams setTimeFormat(int timeFormat) {
		put(TIME_FORMAT, String.valueOf(timeFormat));
		return this;
	}
	
	public BiHuParams setShowAutoMoldCode(int showAutoMoldCode) {
		put(SHOW_AUTO_MOLD_CODE, String.valueOf(showAutoMoldCode));
		return this;
	}
	
	public BiHuParams setCustKey(String custKey) {
		put(CUST_KEY, custKey);
		return this;
	}
}
