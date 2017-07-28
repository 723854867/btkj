package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.community.pojo.param.ArticleListParam;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

public class ARTICLE_LIST extends UserAction<ArticleListParam> {

	@Resource
	private CommunityService communityService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, ArticleListParam param) {
		param.setAppId(user.getAppId());
		return communityService.articles(param);
	}
	
	@Override
	protected Client client() {
		return Client.TENANT_MANAGER;
	}
}
