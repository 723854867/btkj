package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 用户操作，需要登录才可以进行的操作
 * 
 * @author ahab
 */
public abstract class UserAction implements Action {
	
	@Resource
	protected UserService userService;

	@Override
	public Result<?> execute(Request request) {
		String token = request.getHeader(Params.TOKEN);
		Client client = client(request);
		if (userLock()) {
			Result<User> result = userService.lockUserByToken(client, token);
			if (!result.isSuccess())
				return result;
			try {
				return execute(request, result.attach());
			} finally {
				userService.releaseUserLock(result.getDesc(), result.attach().getUid());
			}
		} else {
			User user = userService.getUserByToken(client, token);
			if (null == user)
				return Result.result(Code.TOKEN_INVALID);
			return execute(request, user);
		}
	}
	
	protected abstract Result<?> execute(Request request, User user);
	
	/**
	 * 如果返回 true 则已经获取到了 user 的锁
	 * 
	 * @return
	 */
	protected boolean userLock() {
		return false;
	}
}
