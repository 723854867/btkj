package org.btkj.manager.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.info.config.ModularDocument;
import org.btkj.pojo.param.IdParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class MODULARS_USER extends UserAction<IdParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(AppPO app, UserPO user, IdParam param) {
		if (!BtkjUtil.isTopRole(user))
			return Consts.RESULT.NO_PRIVILEGE;
		if (param.getId() == user.getUid())
			return Consts.RESULT.FORBID;
		UserPO target = userService.user(param.getId());
		if (null == target)
			return Consts.RESULT.USER_NOT_EXIST;
		if (target.getAppId() != user.getAppId())
			return Consts.RESULT.FORBID;
		return Result.result(configManageService.modulars(param.getId(), ModularType.USER));
	}
}
