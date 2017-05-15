package org.btkj.pojo.enums;

public enum AppState {

	NORMAL(0),
	FORBID(1);
	
	private int mark;
	private AppState(int mark) {
		this.mark = mark;
	}
	public int mark() {
		return mark;
	}
	public static final AppState match(int state) {
		for (AppState temp : AppState.values()) {
			if (temp.mark == state)
				return temp;
		}
		return null;
	}
}
