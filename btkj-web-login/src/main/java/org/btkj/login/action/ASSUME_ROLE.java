package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.AliyunService;
import org.btkj.login.model.AssumeRoleType;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.StsInfo;
import org.btkj.pojo.model.UserModel;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

public class ASSUME_ROLE implements Action {
	
	@Resource
	private UserService userService;
	@Resource
	private AliyunService aliyunService;

	@Override
	public Result<StsInfo> execute(Request request) {
		AssumeRoleType type = AssumeRoleType.match(request.getParam(Params.TYPE));
		if (null == type)
			throw ConstConvertFailureException.errorConstException(Params.TYPE);
		Client client = request.getParam(Params.CLIENT);
		switch (type) {
		case USER:
			String token = request.getHeader(Params.TOKEN);
			UserModel userModel = userService.getUserByToken(client, token);
			if (null == userModel)
				return Result.result(Code.TOKEN_INVALID);
			return Result.result(aliyunService.assumeRole(userModel.getUser()));
		default:
			break;
		}
		return null;
	}
}
