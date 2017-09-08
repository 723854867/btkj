package org.btkj.manager.action.user;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.IdParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class USER_SEAL extends UserAction<IdParam> {
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, IdParam param) {
		if (user.getUid() == param.getId())
			return Consts.RESULT.FORBID;
		return userService.seal(app.getId(), param.getId());
	}
}
