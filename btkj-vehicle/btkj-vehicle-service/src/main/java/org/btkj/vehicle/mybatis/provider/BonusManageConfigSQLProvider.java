package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BonusManageConfigSQLProvider {

	private final String TABLE			= "bonus_manage_config";
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("key", "#{key}");
				VALUES("tid", "#{tid}");
				VALUES("type", "#{type}");
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
				WHERE("key=#{key}");
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
				SET("`rate`=#{rate}");
				SET("updated=#{updated}");
				WHERE("key=#{key}");
			}
		}.toString();
	}
	
	public String delete() {
		return new SQL() {
			{
				DELETE_FROM(TABLE);
				WHERE("key=#{key}");
			}
		}.toString();
	}
}
