package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.model.identity.User;
import org.btkj.web.util.Params;
import org.btkj.web.util.action.OldUserAction;
import org.btkj.web.util.action.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public class QUIZ extends OldUserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Integer> execute(Request request, User user) {
		return Result.result(Code.OK, communityService.quiz(user.getAppId(), user.getUid(), request.getParam(Params.CONTENT)));
	}
}
