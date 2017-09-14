package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.model.identity.User;
import org.btkj.web.util.Params;
import org.btkj.web.util.action.OldUserAction;
import org.btkj.web.util.action.Request;
import org.rapid.util.common.message.Result;

/**
 * 评论给
 * 
 * @author ahab
 */
public class COMMENT extends OldUserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Void> execute(Request request, User user) {
		int articleId = request.getParam(Params.ID);
		String content = request.getParam(Params.CONTENT);
		return communityService.comment(user, articleId, content);
	}
}
