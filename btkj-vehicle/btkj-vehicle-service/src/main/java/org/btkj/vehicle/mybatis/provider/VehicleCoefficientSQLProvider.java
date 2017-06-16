package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class VehicleCoefficientSQLProvider {
	
	private static final String TABLE			= "vehicle_coefficient";

	public String getByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
}
