package org.btkj.common.action;

import org.btkj.common.Beans;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.Credential;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 需要获取用户锁的操作：串行执行
 * 
 * @author ahab
 */
public abstract class SerialAction extends TenantAction implements Beans {

	@Override
	protected final Result<?> execute(Request request, Credential credential) {
		String token = request.getParam(Params.TOKEN);
		Result<User> result = userService.lockUserByToken(credential.getClientType(), credential.getApp(), token);
		if (!result.isSuccess())
			return result;
		User user = result.attach();
		String lockId = result.getDesc();
		try {
			return execute(request, credential, user);
		} finally {
			userService.releaseUserLock(lockId, user.getUid());
		}
	}
	
	protected abstract Result<?> execute(Request request, Credential credential, User user);
}
