package org.btkj.config.pojo.model;

/**
 * 三级行政区划类型：县级行政区
 * 
 * @author ahab
 */
public enum CountyCantonType {

	// 县
	COUNTY(1),
	
	// 市区
	CITY_DISTRICT(2),
	
	// 县级市
	COUNTY_CITY(3),
	
	// 旗
	BANNER(4),
	
	// 自治县
	AUTONOMOUS_COUNTY(5),
	
	// 省辖县
	COUNTY_PROVINCIAL(6),
	
	// 自治旗
	AUTONOMOUS_BANNER(7),
	
	// 林区
	FOREST_DISTRICT(8);
	
	private int mark;
	
	private CountyCantonType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final CountyCantonType match(int type) {
		for (CountyCantonType temp : CountyCantonType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
