package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class CitySQLProvider {
	
	private static final String TABLE			= "city";

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("code=#{key}");
			}
		}.toString();
	}
}
