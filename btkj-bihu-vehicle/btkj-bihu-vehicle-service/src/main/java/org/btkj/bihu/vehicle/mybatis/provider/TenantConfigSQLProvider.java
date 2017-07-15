package org.btkj.bihu.vehicle.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class TenantConfigSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "tenant_config";
	
	public TenantConfigSQLProvider() {
		super(TABLE, "tid");
	}
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`tid`", "#{tid}");
				VALUES("`agent`", "#{agent}");
				VALUES("`key`", "#{key}");
				VALUES("`created`", "#{created}");
				VALUES("`updated`", "#{updated}");
			}
		}.toString();
	}
	
	public String update() {
		return new SQL(){
			{
				UPDATE(TABLE);
				SET("`agent`=#{agent}");
				SET("`key`=#{key}");
				SET("`updated`=#{updated}");
				WHERE("`tid`=#{tid}");
			}
		}.toString();
	}
}
