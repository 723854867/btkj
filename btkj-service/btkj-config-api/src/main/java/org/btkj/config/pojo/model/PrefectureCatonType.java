package org.btkj.config.pojo.model;

/**
 * 二级行政区划类型：地级行政区
 * 
 * @author ahab
 */
public enum PrefectureCatonType {
	
	// 地级市
	PREFECTURE_CITY(1),

	// 盟
	LEAGUE(2),

	// 自治州
	AUTONOMOUS_PREFECTURE(3),
	
	// 地区
	DISTRICT(4),
	
	// 市辖区和县一般是直辖市下的划分概念，相当于城区和直辖市下的县，和普通的省的市是一级的
	// 市辖区
	MUNICIPAL_DISTRICT(5),
	
	// 直辖市县
	COUNTY_MUNICIPAL(6),
	
	// 省直辖县级行政区划
	COUNTY_PROVINCIAL(7);
	
	private int mark;
	
	private PrefectureCatonType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final PrefectureCatonType match(int type) {
		for (PrefectureCatonType temp : PrefectureCatonType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
