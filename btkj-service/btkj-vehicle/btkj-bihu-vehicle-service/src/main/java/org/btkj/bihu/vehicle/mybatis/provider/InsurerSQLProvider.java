package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.bihu.vehicle.mybatis.Tables;

public class InsurerSQLProvider {

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Tables.INSURER.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
}
