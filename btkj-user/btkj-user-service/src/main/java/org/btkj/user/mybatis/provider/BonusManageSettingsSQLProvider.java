package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BonusManageSettingsSQLProvider {

	private final String TABLE			= "bonus_manage_settings";
	
	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("key=#{key}");
			}
		}.toString();
	}
	
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
