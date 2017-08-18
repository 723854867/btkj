package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.Param;
import org.btkj.user.api.UserService;
import org.btkj.user.pojo.model.UserHolder;
import org.btkj.web.util.Params;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public abstract class UserAction<PARAM extends Param> extends Action<PARAM> {
	
	@Resource
	protected UserService userService;
	
	public UserAction() {
		super();
	}
	
	public UserAction(CrudType... crudTypes) {
		super(crudTypes);
	}

	@Override
	protected Result<?> execute(PARAM param) {
		String token = request().getHeader(Params.TOKEN);
		Client client = client();
		if (userLock()) {
			Result<UserHolder> result = userService.userLockByToken(client, token);
			if (!result.isSuccess())
				return result;
			try {
				return execute(result.attach().getApp(), result.attach().getUser(), param);
			} finally {
				userService.releaseUserLock(result.getDesc(), result.attach().getUser().getUid());
			}
		} else {
			UserHolder uh = userService.userByToken(client, token);
			if (null == uh)
				return Result.result(Code.TOKEN_INVALID);
			return execute(uh.getApp(), uh.getUser(), param);
		}
	}
	
	protected abstract Result<?> execute(AppPO app, UserPO user, PARAM param);
	
	/**
	 * 默认需要客户端传递 client 参数
	 * 
	 * @return
	 */
	public Client client() {
		return request().getParam(Params.CLIENT);
	}
	
	/**
	 * 是否需要获取用户锁
	 * 
	 * @return
	 */
	protected boolean userLock() {
		return false;
	}
}
