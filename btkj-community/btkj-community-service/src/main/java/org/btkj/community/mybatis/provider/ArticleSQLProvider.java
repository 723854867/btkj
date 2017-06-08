package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class ArticleSQLProvider {
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.ARTICLE.name());
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
	
	public String selectByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.ARTICLE.name());
				WHERE("id=#{key}");
			}
		}.toString();
	}
	
	public String selectByAppIdForUpdate() {
		return "SELECT * FROM article WHERE app_id=#{appId} FOR UPDATE";
	}

	public String selectAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(BtkjTables.ARTICLE.name());
			}
		}.toString();
	};
	
	public String update() { 
		return new SQL() {
			{
				UPDATE(BtkjTables.ARTICLE.name());
				SET("browse_num=#{browseNum}");
				SET("comment_num=#{commentNum}");
				WHERE("id=#{id}");
			}
		}.toString();
	}
}
