package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.manager.action.PlatformAction;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
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
	protected Result<Void> execute(Request request, AppPO app, UserPO operator) {
		String title = request.getParam(Params.TITLE);
		String icon = request.getParam(Params.ICON);
		String link = request.getParam(Params.LINK);
		return communityService.articleAdd(app, title, icon, link);
	}
}
