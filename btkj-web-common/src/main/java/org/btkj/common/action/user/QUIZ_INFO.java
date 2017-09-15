package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.QuizInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.community.Quiz;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 提问详情
 * 
 * @author ahab
 */
public class QUIZ_INFO extends UserAction<IdParam> {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<?> execute(App app, User user, IdParam param) {
		Quiz quiz = communityService.quiz(param.getId());
		if (null == quiz || quiz.getAppId() != user.getAppId())
			return BtkjConsts.RESULT.QUIZ_NOT_EXIST;
		User publisher = userService.user(quiz.getUid());
		return Result.result(new QuizInfo(app, publisher, quiz));
	}
}
