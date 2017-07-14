package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class TenantConfigSQLProvider {
	
	private static final String TABLE			= "tenant_config";
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`tid`", "#{tid}");
				VALUES("`agent`", "#{agent}");
				VALUES("`key`", "#{key}");
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
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL(){
			{
				UPDATE(TABLE);
				SET("`agent`=#{agent}");
				SET("`key`=#{key}");
				SET("`updated`=#{updated}");
				WHERE("`tid`=#{tid}");
			}
		}.toString();
	}
	
	public String delete() { 
		return new SQL() {
			{
				DELETE_FROM(TABLE);
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
}
