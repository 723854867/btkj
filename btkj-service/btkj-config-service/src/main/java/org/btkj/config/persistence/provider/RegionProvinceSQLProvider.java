package org.btkj.config.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class RegionProvinceSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.REGION_PROVINCE.name());
				VALUES("code", "#code");
				VALUES("name", "#name");
				VALUES("type", "#type");
				VALUES("plate_title", "#plateTitle");
			}
		}.toString();
	} 
	
	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REGION_PROVINCE.name());
			}
		}.toString();
	}

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REGION_PROVINCE.name());
				WHERE("code=#{key}");
			}
		}.toString();
	}
}
