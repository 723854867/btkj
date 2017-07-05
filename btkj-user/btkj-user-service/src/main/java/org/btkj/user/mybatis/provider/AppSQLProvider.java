package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class AppSQLProvider {
	
	private static final String TABLE			= "app";
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`name`", "#{name}");
				VALUES("region", "#{region}");
				VALUES("`mod`", "#{mod}");
				VALUES("max_tenants_count", "#{maxTenantsCount}");
				VALUES("max_articles_count", "#{maxArticlesCount}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("`name`=#{name}");
				SET("updated=#{updated}");
				SET("state=#{state}");
				SET("take_score=#{takeScore}");
				SET("insurance_open=#{insuranceOpen}");
				SET("consult_open=#{consultOpen}");
				SET("mall_open=#{mallOpen}");
				SET("state=#{state}");
				WHERE("id=#{id}");
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
	
	public String getByKeyForUpdate() {
		return "select * from app where id=#{id} for update";
	}
}
