package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.user.TenantSetPTParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class PLATFORM_TENANT_SET extends UserAction<TenantSetPTParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(App app, User user, TenantSetPTParam param) {
		return userManageService.tenantSet(user, param);
	}
}
