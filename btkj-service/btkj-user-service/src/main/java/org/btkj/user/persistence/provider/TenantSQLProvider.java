package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class TenantSQLProvider {

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
