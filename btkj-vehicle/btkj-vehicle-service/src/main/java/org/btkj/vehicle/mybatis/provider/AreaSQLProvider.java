package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class AreaSQLProvider {
	
	private static final String TABLE			= "area";
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`code`", "#{code}");
				VALUES("`name`", "#{name}");
				VALUES("`renewal_period`", "#{renewalPeriod}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("code=#{key}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL(){
			{
				UPDATE(TABLE);
				SET("`name`=#{name}");
				SET("`renewal_period`=#{renewalPeriod}");
				SET("`updated`=#{updated}");
				WHERE("`code`=#{code}");
			}
		}.toString();
	}
	
	public String delete() { 
		return new SQL() {
			{
				DELETE_FROM(TABLE);
				WHERE("`code`=#{code}");
			}
		}.toString();
	}
}
