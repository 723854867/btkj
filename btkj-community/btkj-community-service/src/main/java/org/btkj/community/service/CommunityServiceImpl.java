package org.btkj.community.service;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.community.redis.ArticleMapper;
import org.btkj.community.redis.CommentMapper;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Article;
import org.btkj.pojo.entity.Comment;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.ArticleSearcher;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {
	
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private CommentMapper commentMapper;

	@Override
	public Result<Pager<Article>> articles(int appId, ArticleSearcher searcher) {
		return articleMapper.articles(appId, searcher);
	}
	
	@Override
	public Result<Pager<Comment>> comments(int appId, int articleId, int page, int pageSize) {
		Article article = articleMapper.getByKey(articleId);
		if (null == article || article.getAppId() != appId)
			return BtkjConsts.RESULT.ARTICLE_NOT_EXIST;
		return commentMapper.comments(articleId, page, pageSize);
	}
}
