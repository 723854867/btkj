package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.IdParam;
import org.rapid.util.common.message.Result;

public class REPLY_DELETE extends UserAction<IdParam> {
	
	@Resource
	private CommunityManageService communityManageService;

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, IdParam param) {
		return communityManageService.replyDelete(param.getId(), app.getId());
	}
}
