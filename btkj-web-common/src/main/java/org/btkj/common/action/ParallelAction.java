package org.btkj.common.action;

import org.btkj.common.Beans;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 不需要获取用户锁：并行执行
 * 
 * @author ahab
 */
public abstract class ParallelAction extends TenantAction implements Beans {

	@Override
	protected Result<?> execute(Request request, Credential credential) {
		String token = request.getParam(Params.TOKEN);
		User user = userService.getUserByToken(credential.getClientType(), credential.getApp(), token);
		if (null == user)
			return Result.result(Code.TOKEN_INVALID);
		return execute(request, credential, user);
	}

	protected abstract Result<?> execute(Request request, Credential credential, User user);
}
