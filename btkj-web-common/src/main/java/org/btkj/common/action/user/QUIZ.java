package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.QuizParam;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public class QUIZ extends UserAction<QuizParam> {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Integer> execute(App app, User user, QuizParam param) {
		return Result.result(Code.OK, communityService.quiz(user.getAppId(), user.getUid(), param.getContent()));
	}
}
