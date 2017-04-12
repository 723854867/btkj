package org.btkj.web.util.action;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 租户接口：
 * 
 * @author ahab
 */
public abstract class TenantAction extends UserAction {
	
	@Override
	protected Result<?> execute(Request request, Client client, App app, User user) {
		int tid = request.getParam(Params.TID);
		Tenant tenant = cacheService.getById(BtkjTables.TENANT.name(), tid);
		if (null == tenant)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		if (tenant.getAppId() != app.getId())
			return Result.result(Code.FORBID);
		return execute(request, client, app, tenant, user);
	}
	
	protected abstract Result<?> execute(Request request, Client client, App app, Tenant tenant, User user);
}
