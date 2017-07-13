package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class AreaSQLProvider {
	
	private static final String TABLE			= "area";

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
