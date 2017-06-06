package org.btkj.common.action.community;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 回复
 * 
 * @author ahab
 */
public class REPLY extends UserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Void> execute(Request request, App app, Client client, User user) {
		int quizId = request.getParam(Params.ID);
		String content = request.getParam(Params.CONTENT);
		return communityService.reply(user, quizId, content);
	}
}