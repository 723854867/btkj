package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class VehicleDeptSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "vehicle_dept";
	
	public VehicleDeptSQLProvider() {
		super(TABLE, "id");
	}

	public String getByBrandId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`brand_id`=#{brandId}");
			}
		}.toString();
	}
}
