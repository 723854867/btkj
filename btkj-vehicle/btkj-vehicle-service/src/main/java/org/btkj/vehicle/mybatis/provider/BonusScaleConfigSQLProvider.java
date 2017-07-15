package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class BonusScaleConfigSQLProvider extends SQLProvider {

	private static final String TABLE			= "bonus_scale_config";
	
	public BonusScaleConfigSQLProvider() {
		super(TABLE, "id");
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
}
