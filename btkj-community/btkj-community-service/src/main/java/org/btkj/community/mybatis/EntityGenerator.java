package org.btkj.community.mybatis;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Article;
import org.btkj.pojo.po.Comment;
import org.btkj.pojo.po.Quiz;
import org.btkj.pojo.po.Reply;
import org.btkj.pojo.po.UserPO;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {

	public static final Article article(AppPO app, String title, String icon, String link) {
		Article article = new Article();
		article.setAppId(app.getId());
		article.setIcon(icon);
		article.setLink(link);
		article.setTitle(title);
		article.setCreated(DateUtil.currentTime());
		return article;
	}
	
	public static final Comment comment(UserPO user, int articleId, String content) {
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setContent(content);
		comment.setUid(user.getUid());
		comment.setCreated(DateUtil.currentTime());
		return comment;
	}
	
	public static final Quiz quiz(UserPO user, String content) {
		Quiz quiz = new Quiz();
		quiz.setAppId(user.getAppId());
		quiz.setUid(user.getUid());
		quiz.setContent(content);
		quiz.setCreated(DateUtil.currentTime());
		return quiz;
	}
	
	public static final Reply reply(UserPO user, int quizId, String content) {
		Reply reply = new Reply();
		reply.setQuizId(quizId);
		reply.setContent(content);
		reply.setUid(user.getUid());
		reply.setCreated(DateUtil.currentTime());
		return reply;
	}
}
