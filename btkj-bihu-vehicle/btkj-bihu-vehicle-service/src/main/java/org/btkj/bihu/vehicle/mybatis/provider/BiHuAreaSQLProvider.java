package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BiHuAreaSQLProvider {
	
	private static final String TABLE			= "bi_hu_area";
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`code`", "#{code}");
				VALUES("cid", "#{cid}");
				VALUES("`name`", "#{name}");
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
				WHERE("code=#{code}");
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
	
	public String update() {
		return new SQL(){
			{
				UPDATE(TABLE);
				SET("cid=#{cid}");
				SET("`name`=#{name}");
				SET("updated=#{updated}");
				WHERE("`code`=#{code}");
			}
		}.toString();
	}
	
	public String delete() { 
		return new SQL() {
			{
				DELETE_FROM(TABLE);
				WHERE("code=#{code}");
			}
		}.toString();
	}
}
