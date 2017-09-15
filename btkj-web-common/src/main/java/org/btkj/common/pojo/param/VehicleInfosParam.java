package org.btkj.common.pojo.param;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.validator.custom.CarVin;

public class VehicleInfosParam extends EmployeeParam {

	private static final long serialVersionUID = 4622819238231783858L;

	@CarVin
	@NotNull
	private String vin;
	
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
}
