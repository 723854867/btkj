package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class TenantSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.TENANT.name());
				VALUES("`name`", "#{name}");
				VALUES("app_id", "#{appId}");
				VALUES("region_id", "#{regionId}");
				VALUES("`mod`", "#{mod}");
				VALUES("`privilege`", "#{privilege}");
				VALUES("`pwd`", "#{pwd}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.TENANT.name());
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.TENANT.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
}
