package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BonusScaleConfigSQLProvider {

	private final String TABLE			= "bonus_scale_config";
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("tid", "#{tid}");
				VALUES("min", "#{min}");
				VALUES("max", "#{max}");
				VALUES("rate", "#{rate}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String getByKey() {
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
	
	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("min", "#{min}");
				SET("max", "#{max}");
				SET("rate", "#{rate}");
				SET("updated", "#{updated}");
				WHERE("id=#{key}");
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
