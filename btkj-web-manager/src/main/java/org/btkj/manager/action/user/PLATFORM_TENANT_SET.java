package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.param.TenantSetPTParam;
import org.rapid.util.common.message.Result;

public class PLATFORM_TENANT_SET extends UserAction<TenantSetPTParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantSetPTParam param) {
		return userManageService.tenantSet(user, param);
	}
}
