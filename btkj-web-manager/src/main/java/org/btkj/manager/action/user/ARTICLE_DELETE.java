package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.IdParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.action.UserOldAction;
import org.rapid.util.common.message.Result;

public class ARTICLE_DELETE extends UserOldAction<IdParam> {
	
	@Resource
	private CommunityManageService communityManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, IdParam param) {
		return communityManageService.articleDelete(param.getId(), user.getAppId());
	}
	
	@Override
	protected Client client() {
		return Client.TENANT_MANAGER;
	}
}
