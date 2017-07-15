package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class QuizSQLProvider extends SQLProvider {
	
	private static final String TABLE				= "quiz";
	
	public QuizSQLProvider() {
		super(TABLE, "id");
	}
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`app_id`", "#{appId}");
				VALUES("`reply_num`", "#{replyNum}");
				VALUES("`uid`", "#{uid}");
				VALUES("`browse_num`", "#{browseNum}");
				VALUES("`content`", "#{content}");
				VALUES("`created`", "#{created}");
			}
		}.toString();
	}
	
	public String update() { 
		return new SQL() {
			{
				UPDATE(TABLE);
				SET("`browse_num`=#{browseNum}");
				SET("`reply_num`=#{replyNum}");
				WHERE("`id`=#{id}");
			}
		}.toString();
	}
	
	public String getByAppId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`app_id`=#{appId}");
			}
		}.toString();
	}
}
