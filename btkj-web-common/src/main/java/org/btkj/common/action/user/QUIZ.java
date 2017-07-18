package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public class QUIZ extends UserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Integer> execute(Request request, User user) {
		return Result.result(Code.OK, communityService.quiz(user.getAppId(), user.getUid(), request.getParam(Params.CONTENT)));
	}
}
