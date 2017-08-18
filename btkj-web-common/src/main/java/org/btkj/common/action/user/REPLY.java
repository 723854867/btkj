package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.model.identity.User;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OldUserAction;
import org.rapid.util.common.message.Result;

/**
 * 回复
 * 
 * @author ahab
 */
public class REPLY extends OldUserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Void> execute(Request request, User user) {
		int quizId = request.getParam(Params.ID);
		String content = request.getParam(Params.CONTENT);
		return communityService.reply(user.getAppId(), user.getUid(), quizId, content);
	}
}
