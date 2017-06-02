package org.btkj.community.mybatis.provider;

public class CommentSQLProvider {

	public String selectByArticleIdForUpdate() {
		return "SELECT * FROM article WHERE article_id = #{articleId} FOR UPDATE";
	}
}
