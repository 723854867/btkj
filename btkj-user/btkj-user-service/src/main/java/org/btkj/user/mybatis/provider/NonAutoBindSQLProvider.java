package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class NonAutoBindSQLProvider {
	
	private static final String TABLE			= "non_auto_bind";

	public String getByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
}
