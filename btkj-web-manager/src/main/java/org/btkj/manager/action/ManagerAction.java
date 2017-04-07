package org.btkj.manager.action;

import org.btkj.manager.Beans;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.ClientType;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 管理后台的 action 一般只允许管理后台调用
 * 
 * @author ahab
 *
 */
public abstract class ManagerAction extends TenantAction implements Beans {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		String token = request.getParam(Params.TOKEN);
		User operator = userService.getUserByToken(credential.getClientType(), credential.getApp(), token);
		if (null == operator)
			return Result.result(Code.USER_NOT_EXIST);
		return execute(request, credential, operator);
	}
	
	protected abstract Result<?> execute(Request request, Credential credential, User operator);
	
	@Override
	protected int clientTypeMod(Request request, Credential credential) {
		return ClientType.MANAGER.type();
	}
}
