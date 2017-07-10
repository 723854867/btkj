package org.btkj.community.service;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.community.mybatis.EntityGenerator;
import org.btkj.community.mybatis.Tx;
import org.btkj.community.redis.ArticleMapper;
import org.btkj.community.redis.CommentMapper;
import org.btkj.community.redis.QuizMapper;
import org.btkj.community.redis.ReplyMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Article;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.entity.Reply;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.exception.BusinessException;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.ArticleSearcher;
import org.btkj.pojo.submit.QuizSearcher;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {
	
	@Resource
	private Tx tx;
	@Resource
	private QuizMapper quizMapper;
	@Resource
	private ReplyMapper replyMapper;
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private CommentMapper commentMapper;
	
	@Override
	public Result<Void> articleAdd(App app, String title, String icon, String link) {
		try {
			tx.articlesAdd(app, title, icon, link);
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		}
		return Consts.RESULT.OK;
	}

	@Override
	public Result<Pager<Article>> articles(ArticleSearcher searcher) {
		return articleMapper.paging(searcher);
	}
	
	@Override
	public Result<Pager<Comment>> comments(int appId, int articleId, int page, int pageSize) {
		Article article = articleMapper.getByKey(articleId);
		if (null == article || article.getAppId() != appId)
			return BtkjConsts.RESULT.ARTICLE_NOT_EXIST;
		return commentMapper.comments(articleId, page, pageSize);
	}
	
	@Override
	public Result<Void> comment(User user, int articleId, String content) {
		try {
			tx.comment(user, articleId, content).finish();;
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public int quiz(User user, String content) {
		Quiz quiz = EntityGenerator.quiz(user, content);
		quizMapper.insert(quiz);
		return quiz.getId();
	}
	
	@Override
	public Result<Pager<Quiz>> quizs(QuizSearcher searcher) {
		return quizMapper.paging(searcher);
	}
	
	@Override
	public Quiz quiz(int quizId) {
		return quizMapper.getByKey(quizId);
	}
	
	@Override
	public Result<Pager<Reply>> replies(int appId, int quizId, int page, int pageSize) {
		Quiz quiz = quizMapper.getByKey(quizId);
		if (null == quiz || quiz.getAppId() != appId)
			return BtkjConsts.RESULT.QUIZ_NOT_EXIST;
		return replyMapper.paging(quizId, page, pageSize);
	}
	
	@Override
	public Result<Void> reply(User user, int quizId, String content) {
		try {
			tx.reply(user, quizId, content).finish();;
		} catch (BusinessException e) {
			return Result.result(e.getCode());
		}
		return Consts.RESULT.OK;
	}
}
