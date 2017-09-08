package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
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
	protected Result<?> execute(AppPO app, UserPO user, ArticleListParam param) {
		param.setAppId(user.getAppId());
		return communityService.articles(param);
	}
}
