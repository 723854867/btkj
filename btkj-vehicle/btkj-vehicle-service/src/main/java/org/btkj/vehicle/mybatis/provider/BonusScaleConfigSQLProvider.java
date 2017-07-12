package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BonusScaleConfigSQLProvider {

	private final String TABLE			= "bonus_scale_config";
	
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
