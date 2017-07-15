package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class ArticleSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "article";
	
	public ArticleSQLProvider() {
		super(TABLE, "id");
	}
	
	public String insert() { 
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`app_id`", "#{appId}");
				VALUES("`title`", "#{title}");
				VALUES("`browse_num`", "#{browseNum}");
				VALUES("`comment_num`", "#{commentNum}");
				VALUES("`icon`", "#{icon}");
				VALUES("`link`", "#{link}");
				VALUES("`created`", "#{created}");
			}
		}.toString();
	}
	
	public String getByAppIdForUpdate() {
		return "SELECT COUNT(*) FROM article WHERE `app_id`=#{appId} FOR UPDATE";
	}

	public String getByAppId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`app_id`=#{appId}");
			}
		}.toString();
	};
	
	public String update() { 
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("`browse_num`=#{browseNum}");
				SET("`comment_num`=#{commentNum}");
				WHERE("`id`=#{id}");
			}
		}.toString();
	}
}
