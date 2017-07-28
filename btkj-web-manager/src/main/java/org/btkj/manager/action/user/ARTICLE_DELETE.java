package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.community.api.CommunityManageService;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.DeleteParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

public class ARTICLE_DELETE extends UserAction<DeleteParam> {
	
	@Resource
	private CommunityManageService communityManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, DeleteParam param) {
		return communityManageService.articleDelete(param.getId(), user.getAppId());
	}
	
	@Override
	protected Client client() {
		return Client.TENANT_MANAGER;
	}
}
