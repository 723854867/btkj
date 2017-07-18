package org.btkj.common.action.community;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public class QUIZ extends UserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Integer> execute(Request request, AppPO app, Client client, UserPO user) {
		return Result.result(Code.OK, communityService.quiz(user, request.getParam(Params.CONTENT)));
	}
}
