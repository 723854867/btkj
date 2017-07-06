package org.btkj.user.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class BannerSQLProvider {
	
	private static final String TABLE			= "banner";
	
	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("id=#{key}");
			}
		}.toString();
	}

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
	
	public String update() {
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("image=#{image}");
				SET("link=#{link}");
				SET("updated=#{updated}");
				WHERE("id=#{id}");
			}
		}.toString();
	}
	
	public String delete() {
		return new SQL() {
			{
				DELETE_FROM(TABLE);
				WHERE("id=#{id}");
			}
		}.toString();
	}
}
