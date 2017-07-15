package org.btkj.config.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class AreaSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "area";
	
	public AreaSQLProvider() {
		super(TABLE, "code");
	}
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`code`", "#{code}");
				VALUES("`name`", "#{name}");
				VALUES("`renewal_period`", "#{renewalPeriod}");
				VALUES("`bi_hu_id`", "#{biHuId}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}

	public String update() {
		return new SQL(){
			{
				UPDATE(TABLE);
				SET("`name`=#{name}");
				SET("`renewal_period`=#{renewalPeriod}");
				SET("`bi_hu_id`=#{biHuId}");
				SET("`updated`=#{updated}");
				WHERE("`code`=#{code}");
			}
		}.toString();
	}
}
