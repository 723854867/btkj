package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.manager.pojo.param.ArticleAddParam;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.rapid.util.common.message.Result;

/**
 * 新增咨询
 * 
 * @author ahab
 */
public class ARTICLE_ADD extends UserAction<ArticleAddParam> {
	
	@Resource
	private CommunityManageService communityManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, ArticleAddParam param) {
		return communityManageService.articleAdd(user.getAppId(), app.getMaxArticlesCount(), param.getTitle(), param.getIcon(), param.getLink());
	}
}
