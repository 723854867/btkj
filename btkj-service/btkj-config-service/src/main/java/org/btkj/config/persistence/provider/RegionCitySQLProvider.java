package org.btkj.config.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.config.persistence.BtkjTable;

public class RegionCitySQLProvider {

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTable.REGION_CITY.key());
			}
		}.toString();
	}
}
