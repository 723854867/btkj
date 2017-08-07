package org.btkj.config.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class ApiSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "api";

	public ApiSQLProvider() {
		super(TABLE, "pkg", false);
	}
	
	public String getByModularId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(table);
				WHERE("modular_id=#{modularId}");
			}
		}.toString();
	}
}
