package org.btkj.manager.pojo.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.param.EmployeeParam;

public class BonusScaleDetailsParam extends EmployeeParam {

	private static final long serialVersionUID = 6685805739123114930L;

	@NotNull
	@Size(min = 3, max = 12)
	private String key;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
}
