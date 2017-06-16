package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class RouteSQLProvider {
	
	private static final String TABLE			= "route";

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("key=#{key}");
			}
		}.toString();
	}
	
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
