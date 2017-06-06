package org.btkj.community.mybatis;

import javax.annotation.Resource;

import org.btkj.community.mybatis.dao.ArticleDao;
import org.btkj.community.mybatis.dao.QuizDao;
import org.btkj.community.redis.CommentMapper;
import org.btkj.community.redis.ReplyMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Article;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.entity.Reply;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tx")
public class Tx {
	
	@Resource
	private QuizDao QuizDao;
	@Resource
	private ArticleDao articleDao;
	
	@Resource
	private ReplyMapper ReplyMapper;
	@Resource
	private CommentMapper commentMapper;
	

	@Transactional
	public void storeComments(int articleId) {
		commentMapper.storeComments(articleId);
	}
	
	@Transactional
	public void comment(User user, int articleId, String content) {
		Article article = articleDao.selectByKey(articleId);
		if (null == article || article.getAppId() != user.getAppId())
			throw new BusinessException(BtkjCode.ARTICLE_NOT_EXIST);
		article.setCommentNum(article.getCommentNum() + 1);
		articleDao.update(article);
		Comment comment = EntityGenerator.comment(user, articleId, content);
		commentMapper.insert(comment);
	}
	
	@Transactional
	public void reply(User user, int quizId, String content) {
		Quiz quiz = QuizDao.selectByKey(quizId);
		if (null == quiz || quiz.getAppId() != user.getAppId())
			throw new BusinessException(BtkjCode.QUIZ_NOT_EXIST);
		quiz.setReplyNum(quiz.getReplyNum() + 1);
		QuizDao.update(quiz);
		Reply reply = EntityGenerator.reply(user, quizId, content);
		ReplyMapper.insert(reply);
	}
}
