package org.btkj.config.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class PrivilegeSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "privilege";
	
	public PrivilegeSQLProvider() {
		super(TABLE, "id", false);
	}
	
	public String getByTypeAndTarId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`type`=#{type}");
				AND();
				WHERE("`tar_id`=#{tarId}");
			}
		}.toString();
	}
	
	public String deleteByTypeAndTarId() {
		return new SQL() {
			{
				DELETE_FROM(table);
				WHERE("`type`=#{type}");
				AND();
				WHERE("`tar_id`=#{tarId}");
			}
		}.toString();
	}
}
