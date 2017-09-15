package org.btkj.community.api;

import org.btkj.pojo.entity.community.Article;
import org.btkj.pojo.entity.community.Comment;
import org.btkj.pojo.entity.community.Quiz;
import org.btkj.pojo.entity.community.Reply;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.community.ArticleListParam;
import org.btkj.pojo.param.community.QuizListParam;
import org.rapid.util.common.message.Result;

public interface CommunityService {
	
	/**
	 * 咨询分页
	 * 
	 * @param param
	 * @return
	 */
	Result<Pager<Article>> articles(ArticleListParam param);
	
	/**
	 * 评论分页
	 * 
	 * @param appId
	 * @param articleId
	 * @param page
	 * @return
	 */
	Result<Pager<Comment>> comments(int appId, int articleId, int page, int pageSize);
	
	/**
	 * 评论
	 * 
	 * @param user
	 * @param articleId
	 * @param content
	 * @return
	 */
	Result<Void> comment(User user, int articleId, String content); 
	
	/**
	 * 提问
	 * 
	 * @param user
	 * @param content
	 * @return
	 */
	int quiz(int appId, int uid, String content);
	
	/**
	 * 问答分页
	 * 
	 * @param appId
	 * @param searcher
	 * @return
	 */
	Result<Pager<Quiz>> quizs(QuizListParam param);
	
	/**
	 * 获取指定的提问信息
	 * 
	 * @param quizId
	 * @return
	 */
	Quiz quiz(int quizId);
	
	/**
	 * 回复分页
	 * 
	 * @param appId
	 * @param quizId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Result<Pager<Reply>> replies(int appId, int quizId, int page, int pageSize);
	
	/**
	 * 回复
	 * 
	 * @param user
	 * @param quizId
	 * @param content
	 * @return
	 */
	Result<Void> reply(int appId, int uid, int quizId, String content);
}
