package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.bo.indentity.User;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 用户信息检查：返回几种状态：用户不存在、用户密码未设置、用户存在并且已经设置密码了
 * 
 * @author ahab
 */
public class USER_CHECK extends Action {
	
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;

	@Override
	public Result<Void> execute(Request request) {
		User user = userService.user(request.getParam(Params.MOBILE), request.getParam(Params.APP_ID));
		if (null == user)
			return Result.result(Code.USER_NOT_EXIST);
		if (null == user.getPwd())
			return Result.result(Code.PWD_NOT_RESET);
		return Result.success();
	}
}
