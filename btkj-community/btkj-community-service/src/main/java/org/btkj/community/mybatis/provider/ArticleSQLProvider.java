package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class ArticleSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "article";
	
	public ArticleSQLProvider() {
		super(TABLE, "id");
	}
	
	public String countByAppIdForUpdate() {
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
}
