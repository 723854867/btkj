package org.btkj.test.persistence.mapper;

import org.rapid.data.storage.mybatis.SQLProvider;

public class UserSQLProvider extends SQLProvider {

	public UserSQLProvider() {
		super("user", "uid");
	}
}
