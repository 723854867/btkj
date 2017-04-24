package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class CommunitySQLProvider {

	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.COMMUNITY.name());
				WHERE("app_id=#{appId}");
			}
		}.toString();
	}
}
