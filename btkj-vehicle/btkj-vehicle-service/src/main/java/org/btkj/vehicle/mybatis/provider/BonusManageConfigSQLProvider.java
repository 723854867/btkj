package org.btkj.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class BonusManageConfigSQLProvider extends SQLProvider {

	private static final String TABLE			= "bonus_manage_config";
	
	public BonusManageConfigSQLProvider() {
		super(TABLE, "key", false);
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
}
