package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.AliyunService;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.courier.StsInfo;
import org.btkj.pojo.param.NilParam;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

public class STS_USER extends UserAction<NilParam> {
	
	@Resource
	private UserService userService;
	@Resource
	private AliyunService aliyunService;

	@Override
	protected Result<StsInfo> execute(App app, User user, NilParam param) {
		StsInfo stsInfo = aliyunService.assumeRole(user.getAppId(), user.getUid());
		return null == stsInfo ? Result.result(Code.SYSTEM_ERROR) : Result.result(stsInfo);
	}
}
