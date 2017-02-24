package org.btkj.config.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.config.persistence.BtkjTable;

public class InsuranceSQLMapper {

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTable.INSURANCE.key());
			}
		}.toString();
	}
}
