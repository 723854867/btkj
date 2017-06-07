package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.manager.action.PlatformAction;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

/**
 * 新增咨询
 * 
 * @author ahab
 */
public class ARTICLE_ADD extends PlatformAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Void> execute(Request request, App app, User operator) {
		String title = request.getParam(Params.TITLE);
		String icon = request.getParam(Params.ICON);
		String link = request.getParam(Params.LINK);
		return communityService.articleAdd(app, title, icon, link);
	}
}
