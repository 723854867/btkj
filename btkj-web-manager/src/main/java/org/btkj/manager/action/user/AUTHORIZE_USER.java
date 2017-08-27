package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.manager.ManagerUtil;
import org.btkj.manager.action.UserAction;
import org.btkj.manager.pojo.param.AuthorizeParam;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.ModularType;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class AUTHORIZE_USER extends UserAction<AuthorizeParam> {

	@Resource
	private ConfigManageService configManageService;
	
	@Override
	protected Result<Void> execute(AppPO app, UserPO user, AuthorizeParam param) {
		if (user.getUid() == param.getTarId())
			return Consts.RESULT.FORBID;
		UserPO target = userService.user(param.getTarId());
		if (null == target)
			return Consts.RESULT.USER_NOT_EXIST;
		if (target.getAppId() != app.getId())
			return Consts.RESULT.FORBID;
		int srcId = user.getUid();
		TarType srcType = TarType.USER;
		if (ManagerUtil.hasFullPrivileges(user)) {
			srcId = app.getId();
			srcType = TarType.APP;
		}
		configManageService.authorize(srcId, srcType, target.getUid(), TarType.USER, ModularType.APP, param.getModulars());
		return Consts.RESULT.OK;
	}
}
