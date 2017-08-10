package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.Param;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.UserService;
import org.btkj.user.pojo.model.UserHolder;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 用户操作，需要登录才可以进行的操作
 * 
 * @author ahab
 */
public abstract class UserOldAction<PARAM extends Param> extends Action<PARAM> {
	
	@Resource
	protected UserService userService;

	@Override
	public Result<?> execute(Request request) {
		if (null != clazz)
			return super.execute(request);
		else {
			String token = request.getHeader(Params.TOKEN);
			Client client = client(request);
			if (userLock()) {
				Result<User> result = userService.lockUserByToken(client, token);
				if (!result.isSuccess())
					return result;
				try {
					if (!userIntegrityVerify(result.attach()))
						return BtkjConsts.RESULT.USER_DATA_INCOMPLETE;
					return execute(request, result.attach());
				} finally {
					userService.releaseUserLock(result.getDesc(), result.attach().getUid());
				}
			} else {
				User user = userService.getUserByToken(client, token);
				if (null == user)
					return Result.result(Code.TOKEN_INVALID);
				if (!userIntegrityVerify(user))
					return BtkjConsts.RESULT.USER_DATA_INCOMPLETE;
				return execute(request, user);
			}
		}
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
	
	protected Result<?> execute(AppPO app, UserPO user, PARAM param) {
		return Consts.RESULT.OK;
	}
	
	protected Result<?> execute(Request request, User user) {
		return Consts.RESULT.OK;
	}
	
	/**
	 * 检测是否完善用户资料
	 * 
	 * @return
	 */
	protected boolean userIntegrityVerify(User user) {
		UserPO po = user.getEntity();
		return !(null == po.getName() || null == po.getAvatar() || null == po.getIdentity()
				|| null == po.getIdentityBack() || null == po.getIdentityFace());
	}
	
	/**
	 * 如果返回 true 则已经获取到了 user 的锁
	 * 
	 * @return
	 */
	protected boolean userLock() {
		return false;
	}
}
