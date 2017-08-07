package org.btkj.master.pojo.param;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.Param;
import org.rapid.util.validator.custom.Power;

public class ApisParam extends Param {

	private static final long serialVersionUID = 3062267161476232476L;

	@Power
	@NotNull
	private String modularId;
	
	public String getModularId() {
		return modularId;
	}
	
	public void setModularId(String modularId) {
		this.modularId = modularId;
	}
}
