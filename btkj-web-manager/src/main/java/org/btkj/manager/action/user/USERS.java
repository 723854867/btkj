package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.user.UserPagingInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.user.UsersParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class USERS extends UserAction<UsersParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<UserPagingInfo>> execute(AppPO app, UserPO user, UsersParam param) {
		param.setAppId(app.getId());
		param.setClient(Client.TENANT_MANAGER);
		return userManageService.users(param);
	}
}
