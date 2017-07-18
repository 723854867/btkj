package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.QuizInfo;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.Quiz;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 提问详情
 * 
 * @author ahab
 */
public class QUIZ_INFO extends UserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<QuizInfo> execute(Request request, User user) {
		Quiz quiz = communityService.quiz(request.getParam(Params.ID));
		if (null == quiz || quiz.getAppId() != user.getAppId())
			return BtkjConsts.RESULT.QUIZ_NOT_EXIST;
		UserPO publisher = userService.getUser(quiz.getUid());
		return Result.result(new QuizInfo(publisher, quiz));
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return false;
	}
}
