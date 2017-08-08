package org.btkj.master.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.Param;

public class ApisParam extends Param {

	private static final long serialVersionUID = 3062267161476232476L;

	@Min(1)
	private int modularId;
	
	public int getModularId() {
		return modularId;
	}
	
	public void setModularId(int modularId) {
		this.modularId = modularId;
	}
}
