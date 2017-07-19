package org.btkj.login.action.user;

import javax.annotation.Resource;

import org.btkj.courier.api.AliyunService;
import org.btkj.courier.pojo.info.StsInfo;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public class ASSUME_ROLE extends UserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private AliyunService aliyunService;

	@Override
	protected Result<?> execute(Request request, User user) {
		StsInfo stsInfo = aliyunService.assumeRole(user.getAppId(), user.getUid());
		return null == stsInfo ? Result.result(Code.SYSTEM_ERROR) : Result.result(stsInfo);
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
}
