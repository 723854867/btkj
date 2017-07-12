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
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("tid", "#{tid}");
				VALUES("type", "#{type}");
				VALUES("name", "#{name}");
				VALUES("comparison", "#{comparison}");
				VALUES("comparable_value", "#{comparableValue}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("name", "#{name}");
				SET("comparison", "#{comparison}");
				SET("comparable_value", "#{comparableValue}");
				SET("updated", "#{updated}");
				WHERE("id=#{id}");
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
	
	public String getByTidAndTypeForUpdate() {
		return "SELECT * FROM vehicle_coefficient WHERE tid=#{tid} AND type=#{type} FOR UPDATE";
	}
}
