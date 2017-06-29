package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.enums.UnitType;
import org.btkj.pojo.enums.vehicle.VehicleUsedType;

public class PairInfo implements Serializable {

	private static final long serialVersionUID = -244534446830601590L;

	private String id;
	private String name;
	private PairInfo[] units;
	
	public PairInfo(UnitType unitType) {
		this.id = unitType.name();
		this.name = unitType.title();
	}
	
	public PairInfo(VehicleUsedType type) {
		this.id = type.name();
		this.name = type.title();
		this.units = new PairInfo[type.supportUnitTypes().length];
		int idx = 0;
		for (UnitType unitType : type.supportUnitTypes()) 
			this.units[idx++] = new PairInfo(unitType);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
