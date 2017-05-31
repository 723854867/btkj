package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.bihu.vehicle.mybatis.Tables;

public class TenantConfigSQLProvider {

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.TENANT_CONFIG.name());
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
}
