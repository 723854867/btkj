package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.bihu.vehicle.mybatis.Tables;

public class BiHuCitySQLProvider {

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.BI_HU_CITY.name());
				WHERE("code=#{code}");
			}
		}.toString();
	}
}
