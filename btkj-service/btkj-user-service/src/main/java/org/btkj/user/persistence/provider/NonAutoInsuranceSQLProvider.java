package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class NonAutoInsuranceSQLProvider {

	public String selectByAppIdAndTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.NON_AUTO_INSURANCE.name());
				WHERE("app_id=#{appId}");
				AND();
				WHERE("tid=#{tid}");
			}
		}.toString();
	}
}
