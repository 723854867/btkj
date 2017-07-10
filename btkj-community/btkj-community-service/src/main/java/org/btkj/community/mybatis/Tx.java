package org.btkj.community.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.community.mybatis.dao.ArticleDao;
import org.btkj.community.mybatis.dao.CommentDao;
import org.btkj.community.mybatis.dao.QuizDao;
import org.btkj.community.mybatis.dao.ReplyDao;
import org.btkj.community.redis.ArticleMapper;
import org.btkj.community.redis.CommentMapper;
import org.btkj.community.redis.QuizMapper;
import org.btkj.community.redis.ReplyMapper;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.TxCallback;
import org.btkj.pojo.entity.App;
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
	private QuizDao quizDao;
	@Resource
	private ReplyDao replyDao;
	@Resource
	private ArticleDao articleDao;
	@Resource
	private CommentDao commentDao;
	
	@Resource
	private QuizMapper quizMapper;
	@Resource
	private ReplyMapper replyMapper;
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private CommentMapper commentMapper;

	@Transactional
	public TxCallback comment(User user, int articleId, String content) {
		Article article = articleMapper.getByKey(articleId);
		if (null == article || article.getAppId() != user.getAppId())
			throw new BusinessException(BtkjCode.ARTICLE_NOT_EXIST);
		article.setCommentNum(article.getCommentNum() + 1);
		articleDao.update(article);
		Comment comment = EntityGenerator.comment(user, articleId, content);
		commentDao.insert(comment);
		return new TxCallback() {
			@Override
			public void finish() {
				articleMapper.flush(article);
				commentMapper.flush(comment);
			}
		};
	}
	
	@Transactional
	public TxCallback reply(User user, int quizId, String content) {
		Quiz quiz = quizMapper.getByKey(quizId);
		if (null == quiz || quiz.getAppId() != user.getAppId())
			throw new BusinessException(BtkjCode.QUIZ_NOT_EXIST);
		quiz.setReplyNum(quiz.getReplyNum() + 1);
		quizDao.update(quiz);
		Reply reply = EntityGenerator.reply(user, quizId, content);
		replyDao.insert(reply);
		return new TxCallback() {
			@Override
			public void finish() {
				quizMapper.flush(quiz);
				replyMapper.flush(reply);
			}
		};
	}
	
	@Transactional
	public void articlesAdd(App app, String title, String icon, String link) { 
		if (app.getMaxArticlesCount() > 0) {
			List<Article> list = articleDao.getByAppIdForUpdate(app.getId());
			if (list.size() >= app.getMaxArticlesCount())
				throw new BusinessException(BtkjCode.ARTICLE_NUM_MAXIMUM);
		}
		Article article = EntityGenerator.article(app, title, icon, link);
		articleMapper.insert(article);
	}
}
