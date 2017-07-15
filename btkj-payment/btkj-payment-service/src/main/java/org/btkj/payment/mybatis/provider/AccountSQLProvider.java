package org.btkj.payment.mybatis.provider;

import org.rapid.data.storage.mybatis.SQLProvider;

public class AccountSQLProvider extends SQLProvider {
	
	public AccountSQLProvider() {
		super("account", "employee_id");
	}
}
