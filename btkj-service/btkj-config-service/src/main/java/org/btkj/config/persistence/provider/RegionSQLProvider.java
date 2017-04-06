package org.btkj.config.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class RegionSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.REGION.name());
				VALUES("id", "#id");
				VALUES("name", "#name");
				VALUES("level", "#level");
				VALUES("parent_id", "#parentId");
				VALUES("short_name", "#shortName");
			}
		}.toString();
	} 

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REGION.name());
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REGION.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
}
