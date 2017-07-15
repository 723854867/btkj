package org.btkj.config.mybatis.provider;

import org.rapid.data.storage.mybatis.SQLProvider;

public class AreaSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "area";
	
	public AreaSQLProvider() {
		super(TABLE, "code", false);
	}
}
