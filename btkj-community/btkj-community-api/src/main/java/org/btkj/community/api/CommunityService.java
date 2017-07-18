package org.btkj.community.api;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Article;
import org.btkj.pojo.po.Comment;
import org.btkj.pojo.po.Quiz;
import org.btkj.pojo.po.Reply;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.ArticleSearcher;
import org.btkj.pojo.vo.QuizSearcher;
import org.rapid.util.common.message.Result;

public interface CommunityService {
	
	/**
	 * 新增咨询
	 * 
	 * @param app
	 * @param title
	 * @param icon
	 * @param link
	 * @return
	 */
	Result<Void> articleAdd(AppPO app, String title, String icon, String link);

	/**
	 * 咨询分页
	 * 
	 * @param appId
	 * @param searcher
	 * @return
	 */
	Result<Pager<Article>> articles(ArticleSearcher searcher);
	
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
	Result<Void> comment(UserPO user, int articleId, String content); 
	
	/**
	 * 提问
	 * 
	 * @param user
	 * @param content
	 * @return
	 */
	int quiz(UserPO user, String content);
	
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
	Result<Void> reply(UserPO user, int quizId, String content);
}
