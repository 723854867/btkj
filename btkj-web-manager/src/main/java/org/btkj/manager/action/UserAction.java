package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.enums.CrudType;

/**
 * 平台操作：只有后台可以调用
 * 
 * @author ahab
 *
 */
public abstract class UserAction<PARAM extends Param> extends org.btkj.web.util.action.UserAction<PARAM> {
	
	public UserAction() {
		super();
	}
	
	public UserAction(CrudType... crudTypes) {
		super(crudTypes);
	}
	
	@Override
	public Client client() {
		return Client.TENANT_MANAGER;
	}
}
