package org.btkj.community.api;

import org.btkj.pojo.entity.Article;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.ArticleSearcher;
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
}
