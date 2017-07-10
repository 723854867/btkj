package org.btkj.community.service;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.community.redis.ArticleMapper;
import org.btkj.community.redis.CommentMapper;
import org.btkj.community.redis.QuizMapper;
import org.btkj.community.redis.ReplyMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Article;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.entity.Reply;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("communityManageService")
public class CommunityManageServiceImpl implements CommunityManageService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommunityManageService.class);
	
	@Resource
	private QuizMapper quizMapper;
	@Resource
	private ReplyMapper replyMapper;
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private CommentMapper commentMapper;

	@Override
	public Result<Void> quizDelete(int quizId, int appId) {
		Quiz quiz = quizMapper.getByKey(quizId);
		if (null == quiz)
			return BtkjConsts.RESULT.QUIZ_NOT_EXIST;
		if (quiz.getAppId() != appId)
			return Consts.RESULT.FORBID;
		quizMapper.delete(quiz);
		replyMapper.deleteByQuizId(quizId);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> replyDelete(int replyId, int appId) {
		Reply reply = replyMapper.getByKey(replyId);
		if (null == reply)
			return BtkjConsts.RESULT.REPLY_NOT_EXIST;
		Quiz quiz = quizMapper.getByKey(reply.getQuizId());
		if (null == quiz) 
			logger.warn("Reply doesn't related to a quiz!");
		else if (quiz.getAppId() != appId)
			return Consts.RESULT.FORBID;
		replyMapper.delete(reply);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> articleDelete(int articleId, int appId) {
		Article article = articleMapper.getByKey(articleId);
		if (null == article)
			return BtkjConsts.RESULT.ARTICLE_NOT_EXIST;
		if (article.getAppId() != appId)
			return Consts.RESULT.FORBID;
		articleMapper.delete(article);
		return null;
	}
	
	@Override
	public Result<Void> commentDelete(int commentId, int appId) {
		Comment comment = commentMapper.getByKey(commentId);
		if (null == comment)
			return BtkjConsts.RESULT.COMMENT_NOT_EXIST;
		Article article = articleMapper.getByKey(comment.getArticleId());
		if (null == article)
			logger.warn("comment doesn't related to a article!");
		else if (article.getAppId() != appId)
			return Consts.RESULT.FORBID;
		commentMapper.delete(comment);
		return Consts.RESULT.OK;
	}
}
