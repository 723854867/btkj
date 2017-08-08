package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.Param;
import org.btkj.web.util.Request;

/**
 * 平台操作：只有后台可以调用
 * 
 * @author ahab
 *
 */
public abstract class UserAction<PARAM extends Param> extends org.btkj.web.util.action.UserAction<PARAM> {
	
	@Override
	public Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
