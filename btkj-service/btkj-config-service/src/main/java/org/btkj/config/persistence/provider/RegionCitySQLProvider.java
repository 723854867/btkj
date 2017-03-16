package org.btkj.config.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class RegionCitySQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.REGION_CITY.name());
				VALUES("code", "#code");
				VALUES("name", "#name");
				VALUES("type", "#type");
				VALUES("super_code", "#superCode");
			}
		}.toString();
	} 

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REGION_CITY.name());
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REGION_CITY.name());
				WHERE("code=#{key}");
			}
		}.toString();
	}
}
