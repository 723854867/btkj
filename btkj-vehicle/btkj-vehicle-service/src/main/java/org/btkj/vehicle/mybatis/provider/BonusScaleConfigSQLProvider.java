package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class BonusScaleConfigSQLProvider extends SQLProvider {

	private static final String TABLE			= "bonus_scale_config";
	
	public BonusScaleConfigSQLProvider() {
		super(TABLE, "id");
	}
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`tid`", "#{tid}");
				VALUES("`rate`", "#{rate}");
				VALUES("`comparison`", "#{comparision}");
				VALUES("`comparable_value`", "#{comparableValue}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}
	
	public String getByTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`tid`=#{tid}");
			}
		}.toString();
	}
	
	public String getByTidForUpdate() {
		return "SELECT * FROM bonus_scale_config WHERE tid=#{tid} FOR UPDATE";
	}
	
	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("`rate`=#{rate}");
				SET("`comparision`=#{comparision}");
				SET("`comparable_value`=#{comparableValue}");
				SET("`updated`=#{updated}");
				WHERE("`id`=#{key}");
			}
		}.toString();
	}
}
