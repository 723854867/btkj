package org.btkj.manager.action.platform;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class COMMENT_DELETE extends UserAction {
	
	@Resource
	private CommunityManageService communityManageService;

	@Override
	protected Result<?> execute(Request request, User user) {
		return communityManageService.commentDelete(request.getParam(Params.ID), user.getAppId());
	}
}
