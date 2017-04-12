package org.btkj.manager.action;

import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;

/**
 * 管理后台的 action 一般只允许管理后台调用
 * 
 * @author ahab
 *
 */
public abstract class ManagerAction extends TenantAction {
	
	@Override
	protected Client client(Request request) {
		return Client.MANAGER;
	}
}
