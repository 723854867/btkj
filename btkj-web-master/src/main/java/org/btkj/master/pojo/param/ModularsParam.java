package org.btkj.master.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.param.Param;

public class ModularsParam extends Param {

	private static final long serialVersionUID = -5545830024706896279L;

	@Min(1)
	private Integer tarId;
	@NotNull
	private ModularType type;
	
	public Integer getTarId() {
		return tarId;
	}
	
	public void setTarId(Integer tarId) {
		this.tarId = tarId;
	}
	
	public ModularType getType() {
		return type;
	}
	
	public void setType(ModularType type) {
		this.type = type;
	}
}
