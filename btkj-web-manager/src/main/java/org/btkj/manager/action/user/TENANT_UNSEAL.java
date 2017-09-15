package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.message.Result;

public class TENANT_UNSEAL extends UserAction<IdParam> {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<Void> execute(App app, User user, IdParam param) {
		return tenantService.unseal(app.getId(), param.getId());
	}
}
