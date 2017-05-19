package org.btkj.bihu.vehicle.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.bihu.vehicle.persistence.Tables;

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
