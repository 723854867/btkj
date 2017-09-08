package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.message.Result;

public class TENANT_UNSEAL extends UserAction<IdParam> {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, IdParam param) {
		return tenantService.unseal(app.getId(), param.getId());
	}
}
