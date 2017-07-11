package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BonusManageConfigSQLProvider {

	private final String TABLE			= "bonus_manage_config";
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("key", "#{key}");
				VALUES("tid", "#{tid}");
				VALUES("type", "#{type}");
				VALUES("rate", "#{rate}");
				VALUES("created", "#{created}");
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
