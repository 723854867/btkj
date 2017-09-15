package org.btkj.manager.action.user;

import org.btkj.manager.action.UserAction;
import org.btkj.manager.pojo.info.UserInfo;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.NilParam;
import org.rapid.util.common.message.Result;

public class USER_INFO extends UserAction<NilParam> {

	@Override
	protected Result<UserInfo> execute(App app, User user, NilParam param) {
		return Result.result(new UserInfo(user));
	}
}
