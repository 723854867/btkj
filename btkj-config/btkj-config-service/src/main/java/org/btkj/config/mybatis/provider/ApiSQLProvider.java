package org.btkj.config.mybatis.provider;

import org.rapid.data.storage.mybatis.SQLProvider;

public class ApiSQLProvider extends SQLProvider {

	public ApiSQLProvider() {
		super("api", "key", false);
	}
}
