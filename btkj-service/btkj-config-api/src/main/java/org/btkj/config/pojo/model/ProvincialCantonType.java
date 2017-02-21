package org.btkj.config.pojo.model;

/**
 * 一级行政区划类型：省级行政区
 * 
 * @author ahab
 */
public enum ProvincialCantonType {
	// 省
	PROVINCE(1),
	
	// 直辖市
	MUNICIPALITY_CITY(2),
	
	// 自治区
	AUTONOMOUS_REGION(3),
	
	// 特别行政区
	SPECIAL_ADMINISTRATIVE_REGION(4);
	
	private int mark;
	
	private ProvincialCantonType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final ProvincialCantonType match(int type) {
		for (ProvincialCantonType temp : ProvincialCantonType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
