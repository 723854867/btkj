package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;

public class USER_EDIT extends UserAction {
	
	@Resource
	private UserService userService;

	@Override
	protected Result<Void> execute(Request request, App app, Client client, User user) {
		String name = request.getParam(Params.NAME);
		String avatar = request.getParam(Params.AVATAR);
		String identity = request.getParam(Params.IDENTITY);
		String identityFace = request.getParam(Params.IDENTITY_FACE);
		String identityBack = request.getParam(Params.IDENTITY_BACK);
		user.setName(name);
		user.setAvatar(avatar);
		user.setIdentity(identity);
		user.setIdentityFace(identityFace);
		user.setIdentityBack(identityBack);
		return userService.update(user);
	}
}
