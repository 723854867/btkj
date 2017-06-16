package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class VehicleConfigSQLProvider {
	
	private static final String TABLE			= "vehicle_config";

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{key}");
			}
		}.toString();
	}
}
