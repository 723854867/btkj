package org.btkj.config.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class PrivilegeSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "privilege";
	
	public PrivilegeSQLProvider() {
		super(TABLE, "id", false);
	}
	
	public String getByTarTypeAndTarId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tar_type=#{tarType}");
				AND();
				WHERE("tar_id=#{tarId}");
			}
		}.toString();
	}
	
	public String deleteByTarTypeAndTarId() {
		return new SQL() {
			{
				DELETE_FROM(table);
				WHERE("tar_type=#{tarType}");
				AND();
				WHERE("tar_id=#{tarId}");
			}
		}.toString();
	}
}
