package org.btkj.pojo.enums;

public enum TenantState {

	NORMAL(0),
	FORBID(1);
	
	private int mark;
	private TenantState(int mark) {
		this.mark = mark;
	}
	public int mark() {
		return mark;
	}
	public static final TenantState match(int state) {
		for (TenantState temp : TenantState.values()) {
			if (temp.mark == state)
				return temp;
		}
		return null;
	}
}
