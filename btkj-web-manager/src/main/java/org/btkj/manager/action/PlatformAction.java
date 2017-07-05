package org.btkj.manager.action;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

public abstract class PlatformAction extends UserAction {
	
	@Override
	protected final Result<?> execute(Request request, App app, Client client, User operator) {
		return execute(request, app, operator);
	}
	
	protected abstract Result<?> execute(Request request, App app, User operator);

	@Override
	protected Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
