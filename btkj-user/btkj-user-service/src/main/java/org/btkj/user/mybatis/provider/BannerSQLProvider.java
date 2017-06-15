package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BannerSQLProvider {
	
	private static final String TABLE			= "banner";

	public String getByAppIdAndTid() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("tid=#{tid}");
				AND();
				WHERE("app_id=#{appId}");
			}
		}.toString();
	}
}
