package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class TenantInsurerSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "tenant_insurer";
	
	public TenantInsurerSQLProvider() {
		super(TABLE, "key", false);
	}

	public String getByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`tid`=#{tid}");
			}
		}.toString();
	}
}
