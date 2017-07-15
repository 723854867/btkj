package org.btkj.vehicle.mybatis.provider;

import org.rapid.data.storage.mybatis.SQLProvider;

public class VehicleBrandSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "vehicle_brand";
	
	public VehicleBrandSQLProvider() {
		super(TABLE, "id");
	}
}
