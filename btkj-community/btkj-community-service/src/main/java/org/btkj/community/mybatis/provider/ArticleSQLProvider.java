package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class ArticleSQLProvider {
	
	private static final String TABLE			= "article";
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("app_id", "#{appId}");
				VALUES("title", "#{title}");
				VALUES("browse_num", "#{browseNum}");
				VALUES("comment_num", "#{commentNum}");
				VALUES("icon", "#{icon}");
				VALUES("link", "#{link}");
				VALUES("created", "#{created}");
			}
		}.toString();
	}
	
	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String getByAppIdForUpdate() {
		return "SELECT * FROM article WHERE app_id=#{appId} FOR UPDATE";
	}

	public String getAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
			}
		}.toString();
	};
	
	public String update() { 
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("browse_num=#{browseNum}");
				SET("comment_num=#{commentNum}");
				WHERE("id=#{id}");
			}
		}.toString();
	}
}
