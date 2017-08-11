package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.manager.pojo.param.AuthorizeParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class AUTHORIZE_TENANT extends UserAction<AuthorizeParam> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, AuthorizeParam param) {
		TenantPO tenant = tenantService.tenant(param.getTarId());
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		if (tenant.getAppId() != app.getId())
			return Consts.RESULT.FORBID;
		return configManageService.authorizeTenant(app.getId(), param.getTarId(), param.getModulars());
	}
}