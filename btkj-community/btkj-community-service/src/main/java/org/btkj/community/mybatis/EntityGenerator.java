package org.btkj.community.mybatis;

import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Article;
import org.btkj.pojo.po.Comment;
import org.btkj.pojo.po.Quiz;
import org.btkj.pojo.po.Reply;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {

	public static final Article article(int appId, String title, String icon, String link) {
		Article article = new Article();
		article.setAppId(appId);
		article.setIcon(icon);
		article.setLink(link);
		article.setTitle(title);
		article.setCreated(DateUtil.currentTime());
		return article;
	}
	
	public static final Comment comment(User user, int articleId, String content) {
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setContent(content);
		comment.setUid(user.getUid());
		comment.setCreated(DateUtil.currentTime());
		return comment;
	}
	
	public static final Quiz quiz(int appId, int uid, String content) {
		Quiz quiz = new Quiz();
		quiz.setAppId(appId);
		quiz.setUid(uid);
		quiz.setContent(content);
		quiz.setCreated(DateUtil.currentTime());
		return quiz;
	}
	
	public static final Reply reply(int uid, int quizId, String content) {
		Reply reply = new Reply();
		reply.setQuizId(quizId);
		reply.setContent(content);
		reply.setUid(uid);
		reply.setCreated(DateUtil.currentTime());
		return reply;
	}
}
