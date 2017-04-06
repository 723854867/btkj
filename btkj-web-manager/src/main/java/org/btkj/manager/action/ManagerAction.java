package org.btkj.manager.action;

import org.btkj.manager.Beans;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.ClientType;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OpenAction;
import org.rapid.util.common.message.Result;

/**
 * 管理后台的 action 一般只允许管理后台调用
 * 
 * @author ahab
 *
 */
public abstract class ManagerAction extends OpenAction implements Beans {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		String token = request.getParam(Params.TOKEN);
		User operator = userService.getUserByToken(credential.getClientType(), credential.getApp(), token);
		return null;
	}
	
	@Override
	protected int clientTypeMod(Request request, Credential credential) {
		return ClientType.MANAGER.type();
	}
}
