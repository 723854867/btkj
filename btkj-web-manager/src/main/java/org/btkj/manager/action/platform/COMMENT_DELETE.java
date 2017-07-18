package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.action.PlatformAction;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class COMMENT_DELETE extends PlatformAction {
	
	@Resource
	private CommunityManageService communityManageService;

	@Override
	protected Result<Void> execute(Request request, AppPO app, UserPO operator) {
		return communityManageService.commentDelete(request.getParam(Params.ID), app.getId());
	}
}
