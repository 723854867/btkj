package org.btkj.config.mybatis.provider;

import org.rapid.data.storage.mybatis.SQLProvider;

public class RegionSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "region";
	
	public RegionSQLProvider() {
		super(TABLE, "id", false);
	}
}
