package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class AppSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "app";
	
	public AppSQLProvider() {
		super(TABLE, "id");
	}
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`name`", "#{name}");
				VALUES("`region`", "#{region}");
				VALUES("`mod`", "#{mod}");
				VALUES("`max_tenants_count`", "#{maxTenantsCount}");
				VALUES("`max_articles_count`", "#{maxArticlesCount}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("`name`=#{name}");
				SET("`updated`=#{updated}");
				SET("`state`=#{state}");
				SET("`take_score`=#{takeScore}");
				SET("`insurance_open`=#{insuranceOpen}");
				SET("`consult_open`=#{consultOpen}");
				SET("`mall_open`=#{mallOpen}");
				SET("`state`=#{state}");
				WHERE("`id`=#{id}");
			}
		}.toString();
	}
	
	public String getByKeyForUpdate() {
		return "SELECT * FROM app WHERE `id`=#{key} FOR UPDATE";
	}
}
