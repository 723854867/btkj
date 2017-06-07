package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class ArticleSQLProvider {
	
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
