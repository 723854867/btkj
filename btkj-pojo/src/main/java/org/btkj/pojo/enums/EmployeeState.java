package org.btkj.pojo.enums;

public enum EmployeeState {

	NORMAL(0),
	FORBID(1);
	
	private int mark;
	private EmployeeState(int mark) {
		this.mark = mark;
	}
	public int mark() {
		return mark;
	}
	public static final EmployeeState match(int state) {
		for (EmployeeState temp : EmployeeState.values()) {
			if (temp.mark == state)
				return temp;
		}
		return null;
	}
}
