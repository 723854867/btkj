package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.CommentParam;
import org.btkj.community.api.CommunityService;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
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
	protected Result<Void> execute(AppPO app, UserPO user, CommentParam param) {
		return communityService.comment(user, param.getId(), param.getContent());
	}
}
