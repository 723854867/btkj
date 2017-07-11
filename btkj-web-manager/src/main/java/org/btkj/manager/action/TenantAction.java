package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public abstract class TenantAction extends org.btkj.web.util.action.TenantAction {
	
	@Override
	protected final Result<?> execute(Request request, Client client, EmployeeForm ef) {
		return execute(request, ef);
	}
	
	protected abstract Result<?> execute(Request request, EmployeeForm ef);

	@Override
	protected Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
