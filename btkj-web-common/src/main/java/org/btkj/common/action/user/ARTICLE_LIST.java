package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.community.Article;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.community.ArticleListParam;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 咨询列表
 * 
 * @author ahab
 */
public class ARTICLE_LIST extends UserAction<ArticleListParam> {
	
	@Resource
	private CommunityService communityService;
	
	@Override
	protected Result<Pager<Article>> execute(App app, User user, ArticleListParam param) {
		param.setAppId(user.getAppId());
		return communityService.articles(param);
	}
}
