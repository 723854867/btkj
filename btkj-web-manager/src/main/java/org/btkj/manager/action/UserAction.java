package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;

/**
 * 平台操作：只有后台可以调用
 * 
 * @author ahab
 *
 */
public abstract class UserAction extends org.btkj.web.util.action.UserAction {
	
	@Override
	public Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
