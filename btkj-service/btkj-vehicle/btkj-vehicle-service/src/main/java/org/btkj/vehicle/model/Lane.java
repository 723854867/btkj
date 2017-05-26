package org.btkj.vehicle.model;

public enum Lane {

	BI_HU(1),
	LE_BAO_BA(2),
	BAO_TU(3);
	
	private int mark;
	
	private Lane(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static Lane match(int lane) {
		for (Lane temp : Lane.values()) {
			if (temp.mark == lane)
				return temp;
		}
		return null;
	}
}
