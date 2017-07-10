package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.action.PlatformAction;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class REPLY_DELETE extends PlatformAction {
	
	@Resource
	private CommunityManageService communityManageService;

	@Override
	protected Result<Void> execute(Request request, App app, User operator) {
		return communityManageService.replyDelete(request.getParam(Params.ID), app.getId());
	}
}
