package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;

public abstract class TenantAction extends org.btkj.web.util.action.TenantAction {
	
	@Override
	public Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
