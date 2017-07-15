package org.btkj.user.mybatis.provider;

import org.rapid.data.storage.mybatis.SQLProvider;

public class AppSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "app";
	
	public AppSQLProvider() {
		super(TABLE, "id");
	}
	
	public String getByKeyForUpdate() {
		return "SELECT * FROM app WHERE `id`=#{key} FOR UPDATE";
	}
}
