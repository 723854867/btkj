package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class AppSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.APP.name());
				VALUES("`name`", "#{name}");
				VALUES("region", "#{region}");
				VALUES("`mod`", "#{mod}");
				VALUES("max_tenants_count", "#{maxTenantsCount}");
				VALUES("tenant_add_autonomy", "#{tenantAddAutonomy}");
				VALUES("created", "#{created}");
				VALUES("updated", "#{updated}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL() {
			{
				UPDATE(BtkjTables.APP.name());
				SET("`name`=#{name}");
				SET("created=#{created}");
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

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.APP.name());
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.APP.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String selectByKeyForUpdate() {
		return "select * from app where id=#{id} for update";
	}
}
