package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.vehicle.mybatis.Tables;

public class RouteSQLProvider {

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.ROUTE.name());
				WHERE("key=#{key}");
			}
		}.toString();
	}
	
	public String selectByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.ROUTE.name());
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
}
