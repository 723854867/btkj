package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjTables;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.cache.CacheService;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 用户多 client action
 * 
 * @author ahab
 */
public abstract class UserAction implements Action {
	
	@Resource
	protected UserService userService;
	@Resource
	protected CacheService<?> cacheService;

	@Override
	public Result<?> execute(Request request) {
		String token = request.getHeader(Params.TOKEN);
		Client client = client(request);
		if (userLock()) {
			Result<User> result = userService.lockUserByToken(client, token);
			if (!result.isSuccess())
				return result;
			try {
				App app = cacheService.getById(BtkjTables.APP.name(), result.attach().getAppId());
				return execute(request, client, app, result.attach());
			} finally {
				userService.releaseUserLock(result.getDesc(), result.attach().getUid());
			}
		} else {
			User user = userService.getUserByToken(client, token);
			if (null == user)
				return Result.result(Code.TOKEN_INVALID);
			return execute(request, client, cacheService.getById(BtkjTables.APP.name(), user.getAppId()), user);
		}
	}
	
	protected abstract Result<?> execute(Request request, Client client, App app, User user);
	
	/**
	 * 如果返回 true 则已经获取到了 user 的锁
	 * 
	 * @return
	 */
	protected boolean userLock() {
		return false;
	}
	
	/**
	 * 获取 client 类型：默认是需要客户端传递过来的
	 * 
	 * @param request
	 * @return
	 */
	protected Client client(Request request) {
		return request.getParam(Params.CLIENT);
	}
}