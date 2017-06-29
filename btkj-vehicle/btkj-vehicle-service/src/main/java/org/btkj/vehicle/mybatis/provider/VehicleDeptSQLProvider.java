package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class VehicleDeptSQLProvider {
	
	private static final String TABLE			= "vehicle_dept";

	public String getByBrandId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("brand_id=#{brandId}");
			}
		}.toString();
	}
}
