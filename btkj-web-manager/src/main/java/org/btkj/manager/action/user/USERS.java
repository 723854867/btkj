package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.param.UsersParam;
import org.rapid.util.common.message.Result;

public class USERS extends UserAction<UsersParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, UsersParam param) {
		param.setAppId(app.getId());
		param.setClient(Client.TENANT_MANAGER);
		return userManageService.users(param);
	}
}
