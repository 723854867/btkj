package org.btkj.pojo.param;

import javax.validation.constraints.Min;

public class DeleteParam extends Param {

	private static final long serialVersionUID = 1506913018095606059L;

	@Min(1)
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
