package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.ReplyParam;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 回复
 * 
 * @author ahab
 */
public class REPLY extends UserAction<ReplyParam> {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Void> execute(App app, User user, ReplyParam param) {
		return communityService.reply(user.getAppId(), user.getUid(), param.getId(), param.getContent());
	}
}
