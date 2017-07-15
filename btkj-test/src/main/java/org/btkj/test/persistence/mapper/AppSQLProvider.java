package org.btkj.test.persistence.mapper;

import org.rapid.data.storage.mybatis.SQLProvider;

public class AppSQLProvider extends SQLProvider {

	public AppSQLProvider() {
		super("app", "id");
	}
}
