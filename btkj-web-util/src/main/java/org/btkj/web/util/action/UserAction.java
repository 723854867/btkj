package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.bo.UserModel;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 用户多 client action：只能操作同一个 app 中的资源
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
			Result<UserModel> result = userService.lockUserByToken(client, token);
			if (!result.isSuccess())
				return result;
			try {
				return execute(request, result.attach().getApp(), client, result.attach().getUser());
			} finally {
				userService.releaseUserLock(result.getDesc(), result.attach().getUser().getUid());
			}
		} else {
			UserModel userModel = userService.getUserByToken(client, token);
			if (null == userModel)
				return Result.result(Code.TOKEN_INVALID);
			return execute(request, userModel.getApp(), client, userModel.getUser());
		}
	}
	
	protected abstract Result<?> execute(Request request, AppPO app, Client client, UserPO user);
	
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
