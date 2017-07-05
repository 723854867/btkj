package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class TenantConfigSQLProvider {
	
	private static final String TABLE			= "tenant_config";

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{tid}");
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
