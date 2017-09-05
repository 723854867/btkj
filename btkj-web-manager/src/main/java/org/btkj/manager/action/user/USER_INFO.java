package org.btkj.manager.action.user;

import org.btkj.manager.action.UserAction;
import org.btkj.manager.pojo.info.UserInfo;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.param.NilParam;
import org.rapid.util.common.message.Result;

public class USER_INFO extends UserAction<NilParam> {

	@Override
	protected Result<UserInfo> execute(AppPO app, UserPO user, NilParam param) {
		return Result.result(new UserInfo(user));
	}
}
