package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.vehicle.mybatis.Tables;

public class VehicleConfigSQLProvider {

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.VEHICLE_CONFIG.name());
				WHERE("tid=#{key}");
			}
		}.toString();
	}
}
