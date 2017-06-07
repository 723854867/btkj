package org.btkj.community.mybatis;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Article;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.entity.Reply;
import org.btkj.pojo.entity.User;
import org.rapid.util.lang.DateUtils;

public class EntityGenerator {

	public static final Article article(App app, String title, String icon, String link) {
		Article article = new Article();
		article.setAppId(app.getId());
		article.setIcon(icon);
		article.setLink(link);
		article.setTitle(title);
		article.setCreated(DateUtils.currentTime());
		return article;
	}
	
	public static final Comment comment(User user, int articleId, String content) {
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setContent(content);
		comment.setUid(user.getUid());
		comment.setCreated(DateUtils.currentTime());
		return comment;
	}
	
	public static final Reply reply(User user, int quizId, String content) {
		Reply reply = new Reply();
		reply.setQuizId(quizId);
		reply.setContent(content);
		reply.setUid(user.getUid());
		reply.setCreated(DateUtils.currentTime());
		return reply;
	}
}
