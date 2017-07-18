package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

public abstract class PlatformAction extends UserAction {
	
	@Override
	protected final Result<?> execute(Request request, AppPO app, Client client, UserPO operator) {
		return execute(request, app, operator);
	}
	
	protected abstract Result<?> execute(Request request, AppPO app, UserPO operator);

	@Override
	protected Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
