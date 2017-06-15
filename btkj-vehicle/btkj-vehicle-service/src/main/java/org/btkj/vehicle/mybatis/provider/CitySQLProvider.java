package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.vehicle.mybatis.Tables;

public class CitySQLProvider {

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.CITY.name());
				WHERE("code=#{key}");
			}
		}.toString();
	}
}
