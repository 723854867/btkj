package org.btkj.user.persistence.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class BannerSQLProvider {

	public String selectByAppIdAndTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.BANNER.name());
				WHERE("tid=#{tid}");
				AND();
				WHERE("app_id=#{appId}");
			}
		}.toString();
	}
}
