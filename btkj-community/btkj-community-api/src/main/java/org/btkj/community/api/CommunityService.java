package org.btkj.community.api;

import org.btkj.pojo.entity.Article;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.entity.Quiz;
import org.btkj.pojo.entity.Reply;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.ArticleSearcher;
import org.btkj.pojo.submit.QuizSearcher;
import org.rapid.util.common.message.Result;

public interface CommunityService {

	/**
	 * 咨询分页
	 * 
	 * @param appId
	 * @param searcher
	 * @return
	 */
	Result<Pager<Article>> articles(int appId, ArticleSearcher searcher);
	
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
	 * 问答分页
	 * 
	 * @param appId
	 * @param searcher
	 * @return
	 */
	Result<Pager<Quiz>> quizs(QuizSearcher searcher);
	
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
	Result<Void> reply(User user, int quizId, String content);
}
