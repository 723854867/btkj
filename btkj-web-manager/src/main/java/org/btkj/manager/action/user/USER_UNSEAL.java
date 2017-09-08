package org.btkj.manager.action.user;

import org.btkj.manager.action.UserAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.param.IdParam;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class USER_UNSEAL extends UserAction<IdParam> {

	@Override
	protected Result<Void> execute(AppPO app, UserPO user, IdParam param) {
		if (user.getUid() == param.getId())
			return Consts.RESULT.FORBID;
		return userService.unseal(app.getId(), param.getId());
	}
}
