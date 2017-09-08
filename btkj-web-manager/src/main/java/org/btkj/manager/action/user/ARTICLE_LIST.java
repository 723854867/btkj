package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.param.community.ArticleListParam;
import org.rapid.util.common.message.Result;

public class ARTICLE_LIST extends UserAction<ArticleListParam> {

	@Resource
	private CommunityService communityService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, ArticleListParam param) {
		param.setAppId(user.getAppId());
		return communityService.articles(param);
	}
}
