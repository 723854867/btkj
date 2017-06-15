package org.btkj.master.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class AdministratorSQLProvider {
	
	private static final String TABLE				= "administrator";

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("id=#{id}");
			}
		}.toString();
	}
}
