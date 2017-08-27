package org.btkj.vehicle.pojo.enums;

public enum Lane {

	BI_HU(1, "壁虎车险"),
	LE_BAO_BA(2, "乐保吧车险"),
	BAO_TU(3, "保途车险");
	
	private int mark;
	private String title;
	
	private Lane(int mark, String title) {
		this.mark = mark;
		this.title = title;
	}
	
	public int mark() {
		return mark;
	}
	
	public String title() {
		return title;
	}
	
	public static Lane match(int lane) {
		for (Lane temp : Lane.values()) {
			if (temp.mark == lane)
				return temp;
		}
		return null;
	}
}
