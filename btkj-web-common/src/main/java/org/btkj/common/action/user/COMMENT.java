package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.CommentParam;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 评论给
 * 
 * @author ahab
 */
public class COMMENT extends UserAction<CommentParam> {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Void> execute(App app, User user, CommentParam param) {
		return communityService.comment(user, param.getId(), param.getContent());
	}
}
