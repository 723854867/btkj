package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.bihu.vehicle.mybatis.Tables;

public class CityCodeSQLProvider {

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.CITY_CODE.name());
				WHERE("code=#{code}");
			}
		}.toString();
	}
}
