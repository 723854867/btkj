package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.enums.Client;
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
		Client client = request.getParam(Params.CLIENT);
		String mobile = request.getParam(Params.MOBILE);
		
		Result<? extends UserModel> result = null;
		switch (client) {
		case PC:		// 需要渠道码(tid)
			result = tenantService.employee(mobile, request.getParam(Params.TID));
			break;
		case MANAGER: 	// 需要平台码(appId)
			result = userService.getUser(mobile, request.getParam(Params.APP_ID));
			break;
		default:
			return Result.result(Code.FORBID);
		}
		if (!result.isSuccess())
			return result;
		if (null == result.attach().getUser().getPwd())
			return Result.result(Code.PWD_NOT_RESET);
		return Result.success();
	}
}
