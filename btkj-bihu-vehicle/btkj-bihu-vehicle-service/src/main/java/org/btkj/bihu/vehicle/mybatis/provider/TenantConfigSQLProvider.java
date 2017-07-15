package org.btkj.bihu.vehicle.mybatis.provider;

import org.rapid.data.storage.mybatis.SQLProvider;

public class TenantConfigSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "tenant_config";
	
	public TenantConfigSQLProvider() {
		super(TABLE, "tid", false);
	}
}
