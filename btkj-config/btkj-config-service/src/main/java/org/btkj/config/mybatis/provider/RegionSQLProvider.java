package org.btkj.config.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class RegionSQLProvider {
	
	private static final String TABLE			= "region";
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("id", "#id");
				VALUES("name", "#name");
				VALUES("level", "#level");
				VALUES("parent_id", "#parentId");
				VALUES("short_name", "#shortName");
			}
		}.toString();
	} 

	public String getAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
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
}
