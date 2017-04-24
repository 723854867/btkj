package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.model.UserModel;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 输入账号检查是否有密码
 * 
 * @author ahab
 */
public class PWD_CHECK implements Action {
	
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;

	@Override
	public Result<?> execute(Request request) {
		String mobile = request.getParam(Params.MOBILE);
		
		Result<UserModel> result = userService.getUser(mobile, request.getParam(Params.APP_ID));
		if (!result.isSuccess())
			return result;
		if (null == result.attach().getUser().getPwd())
			return Result.result(Code.PWD_NOT_RESET);
		return Result.success();
	}
}
