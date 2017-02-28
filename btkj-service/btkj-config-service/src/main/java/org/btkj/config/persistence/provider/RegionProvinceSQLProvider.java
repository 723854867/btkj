package org.btkj.config.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.config.persistence.Table;

public class RegionProvinceSQLProvider {
	
	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(Table.REGION_PROVINCE.key());
			}
		}.toString();
	}

}
