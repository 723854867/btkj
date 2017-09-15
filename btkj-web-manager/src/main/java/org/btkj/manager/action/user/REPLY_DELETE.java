package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.IdParam;
import org.rapid.util.common.message.Result;

public class REPLY_DELETE extends UserAction<IdParam> {
	
	@Resource
	private CommunityManageService communityManageService;

	@Override
	protected Result<Void> execute(App app, User user, IdParam param) {
		return communityManageService.replyDelete(param.getId(), app.getId());
	}
}
