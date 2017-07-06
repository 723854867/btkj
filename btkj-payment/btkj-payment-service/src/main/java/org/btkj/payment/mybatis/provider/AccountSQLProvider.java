package org.btkj.payment.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class AccountSQLProvider {
	
	private static final String TABLE			= "account";

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("employee_id=#{employeeId}");
			}
		}.toString();
	}
}
