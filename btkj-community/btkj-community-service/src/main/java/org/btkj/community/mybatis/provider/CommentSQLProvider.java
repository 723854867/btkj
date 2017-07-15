package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.data.storage.mybatis.SQLProvider;

public class CommentSQLProvider extends SQLProvider {
	
	private static final String TABLE			= "comment";
	
	public CommentSQLProvider() {
		super(TABLE, "id");
	}
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(TABLE);
				VALUES("`article_id`", "#{articleId}");
				VALUES("`uid`", "#{uid}");
				VALUES("`content`", "#{content}");
				VALUES("`created`", "#{created}");
			}
		}.toString();
	}

	public String getByArticleId() {
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`article_id`=#{articleId}");
			}
		}.toString();
	}
	
	public String deleteByArticleId() { 
		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE);
				WHERE("`aricle_id`=#{articleId}");
			}
		}.toString();
	}
}
