package org.btkj.common.action;

import org.btkj.common.pojo.info.RefreshInfo;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.NilParam;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

public class REFRESH extends UserAction<NilParam> {

	@Override
	protected Result<RefreshInfo> execute(App app, User user, NilParam param) {
		return Result.result(new RefreshInfo());
	}
}
