package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.AliyunService;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.StsInfo;
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
	protected Result<?> execute(Request request, AppPO app, Client client, UserPO user) {
		StsInfo stsInfo = aliyunService.assumeRole(user);
		return null == stsInfo ? Result.result(Code.SYSTEM_ERROR) : Result.result(stsInfo);
	}
}
