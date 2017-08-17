package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.enums.CrudType;

public abstract class EmployeeAction<PARAM extends EmployeeParam> extends org.btkj.web.util.action.EmployeeAction<PARAM> {
	
	public EmployeeAction() {
		super();
	}
	
	public EmployeeAction(CrudType... crudTypes) {
		super(crudTypes);
	}

	@Override
	public Client client() {
		return Client.TENANT_MANAGER;
	}
}
