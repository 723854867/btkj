package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.manager.pojo.param.CommentDeleteParam;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.rapid.util.common.message.Result;

public class COMMENT_DELETE extends UserAction<CommentDeleteParam> {
	
	@Resource
	private CommunityManageService communityManageService;

	@Override
	protected Result execute(App app, User user, CommentDeleteParam param) {
		return communityManageService.commentDelete(param.getId(), app.getId());
	}
}
