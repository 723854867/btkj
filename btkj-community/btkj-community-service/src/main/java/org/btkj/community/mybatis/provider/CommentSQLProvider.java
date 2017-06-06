package org.btkj.community.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;
import org.btkj.pojo.BtkjTables;

public class CommentSQLProvider {
	
	public String insert() {
		return new SQL() {
			{
				INSERT_INTO(BtkjTables.COMMENT.name());
				VALUES("article_id", "#{articleId}");
				VALUES("uid", "#{uid}");
				VALUES("content", "#{content}");
				VALUES("created", "#{created}");
			}
		}.toString();
	}

	public String selectByArticleIdForUpdate() {
		return "SELECT * FROM comment WHERE article_id = #{articleId} FOR UPDATE";
	}
}
