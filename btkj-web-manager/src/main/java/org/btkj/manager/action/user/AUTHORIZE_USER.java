package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.manager.pojo.param.AuthorizeParam;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class AUTHORIZE_USER extends UserAction<AuthorizeParam> {

	@Resource
	private ConfigManageService configManageService;
	
	@Override
	protected Result<Void> execute(AppPO app, UserPO user, AuthorizeParam param) {
		UserPO target = userService.user(param.getTarId());
		if (null == target)
			return Consts.RESULT.USER_NOT_EXIST;
		if (target.getAppId() != app.getId())
			return Consts.RESULT.FORBID;
		return configManageService.authorizeUser(app.getId(), param.getTarId(), param.getModulars());
	}
}
