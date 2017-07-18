package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

/**
 * 新增咨询
 * 
 * @author ahab
 */
public class ARTICLE_ADD extends UserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<?> execute(Request request, User user) {
		String title = request.getParam(Params.TITLE);
		String icon = request.getParam(Params.ICON);
		String link = request.getParam(Params.LINK);
		return communityService.articleAdd(user.getAppId(), user.maxArticleCount(), title, icon, link);
	}
}
