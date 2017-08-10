package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.param.IdParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.rapid.util.common.message.Result;

/**
 * 删除提问
 * 
 * @author ahab
 */
public class QUIZ_DELETE extends UserAction<IdParam> {
	
	@Resource
	private CommunityManageService communityManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, IdParam param) {
		return communityManageService.quizDelete(param.getId(), app.getId());
	}
}
