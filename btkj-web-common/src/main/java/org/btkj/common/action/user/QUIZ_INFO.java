package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.QuizInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.community.Quiz;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.identity.User;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OldUserAction;
import org.rapid.util.common.message.Result;

/**
 * 提问详情
 * 
 * @author ahab
 */
public class QUIZ_INFO extends OldUserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<QuizInfo> execute(Request request, User user) {
		Quiz quiz = communityService.quiz(request.getParam(Params.ID));
		if (null == quiz || quiz.getAppId() != user.getAppId())
			return BtkjConsts.RESULT.QUIZ_NOT_EXIST;
		UserPO publisher = userService.user(quiz.getUid());
		return Result.result(new QuizInfo(publisher, quiz));
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}
