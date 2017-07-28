package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.pojo.param.ArticleAddParam;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.action.UserAction;
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

	@Override
	protected Client client() {
		return Client.TENANT_MANAGER;
	}
}
