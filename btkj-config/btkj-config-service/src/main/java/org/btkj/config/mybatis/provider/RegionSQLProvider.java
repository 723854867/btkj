package org.btkj.config.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class RegionSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "region";
	
	public RegionSQLProvider() {
		super(TABLE, "id");
	}
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`id`", "#{id}");
				VALUES("`name`", "#{name}");
				VALUES("`level`", "#{level}");
				VALUES("`parent_id`", "#{parentId}");
				VALUES("`short_name`", "#{shortName}");
			}
		}.toString();
	} 
}
