package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.EmployeeParam;

public abstract class EmployeeAction<PARAM extends EmployeeParam> extends org.btkj.web.util.action.EmployeeAction<PARAM> {

	@Override
	protected Client client() {
		return Client.TENANT_MANAGER;
	}
}