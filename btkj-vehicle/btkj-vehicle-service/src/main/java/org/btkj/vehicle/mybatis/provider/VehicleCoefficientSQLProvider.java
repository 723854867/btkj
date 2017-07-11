package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class VehicleCoefficientSQLProvider {
	
	private static final String TABLE			= "vehicle_coefficient";
	
	public String getByKey(int id) {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("id=#{key}");
			}
		}.toString();
	}

	public String getByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
	
	public String delete() {
		return new SQL() {
			{
				DELETE_FROM(TABLE);
				WHERE("id=#{key}");
			}
		}.toString();
	}
}
