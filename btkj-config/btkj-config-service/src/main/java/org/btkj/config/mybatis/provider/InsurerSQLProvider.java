package org.btkj.config.mybatis.provider;

import org.rapid.data.storage.mybatis.SQLProvider;

public class InsurerSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "insurer";
	
	public InsurerSQLProvider() {
		super(TABLE, "id");
	}
}
