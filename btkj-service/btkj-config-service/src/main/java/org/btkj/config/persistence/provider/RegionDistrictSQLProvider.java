package org.btkj.config.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class RegionDistrictSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.REGION_DISTRICT.name());
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
				FROM(BtkjTables.REGION_DISTRICT.name());
			}
		}.toString();
	}
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.REGION_DISTRICT.name());
				WHERE("code=#{key}");
			}
		}.toString();
	}
}
