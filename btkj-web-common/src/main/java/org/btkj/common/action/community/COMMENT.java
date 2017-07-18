package org.btkj.common.action.community;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityService;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 评论给
 * 
 * @author ahab
 */
public class COMMENT extends UserAction {
	
	@Resource
	private CommunityService communityService;

	@Override
	protected Result<Void> execute(Request request, AppPO app, Client client, UserPO user) {
		int articleId = request.getParam(Params.ID);
		String content = request.getParam(Params.CONTENT);
		return communityService.comment(user, articleId, content);
	}
}
