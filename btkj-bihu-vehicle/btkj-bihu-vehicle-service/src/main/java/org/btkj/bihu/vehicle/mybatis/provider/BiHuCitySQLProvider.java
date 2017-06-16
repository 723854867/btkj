package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BiHuCitySQLProvider {
	
	private static final String TABLE			= "bi_hu_city";

	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("code=#{code}");
			}
		}.toString();
	}
}
