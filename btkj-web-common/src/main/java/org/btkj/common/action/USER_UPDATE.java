package org.btkj.common.action;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

/**
 * 编辑用户
 */
public class USER_UPDATE extends UserAction {

	@Override
	protected Result<?> execute(Request request, App app, Client client, User user) {
		String name = request.getOptionalParam(Params.NAME);
		if (null != name)
			user.setName(name);
		String identity = request.getOptionalParam(Params.IDENTITY);
		if (null != identity)
			user.setIdentity(identity);
		return userService.update(user);
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
