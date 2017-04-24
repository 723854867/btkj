package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;

public abstract class TenantAction extends org.btkj.web.util.action.TenantAction {

	@Override
	protected Client client(Request request) {
		return Client.PC;
	}
}
