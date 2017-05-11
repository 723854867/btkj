package org.btkj.manager.action;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public abstract class TenantAction extends org.btkj.web.util.action.TenantAction {
	
	@Override
	protected final Result<?> execute(Request request, Client client, App app, Tenant tenant, User user) {
		return execute(request, app, tenant, user);
	}
	
	protected abstract Result<?> execute(Request request, App app, Tenant tenant, User operator);

	@Override
	protected Client client(Request request) {
		return Client.PC;
	}
}
