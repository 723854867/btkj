package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class USER_EDIT extends UserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private JianJieService jianJieService;

	@Override
	protected Result<Void> execute(Request request, App app, Client client, User user) {
		boolean nameChanged = _name(request, user);
		boolean avatarChanged = _avatar(request, user);
		boolean identityChanged = _identity(request, user);
		boolean identityFaceChanged = _identityFace(request, user);
		boolean identityBackChanged = _identityBack(request, user);
		if (!nameChanged && !avatarChanged && !identityChanged && !identityFaceChanged && !identityBackChanged)
			return Consts.RESULT.OK;
		Result<Void> result = userService.update(user);
		if (result.isSuccess() && (nameChanged || identityChanged))
			jianJieService.addUser(user);
		return result;
	}
	
	private boolean _name(Request request, User user) {
		String name = request.getParam(Params.NAME);
		if (null == user.getName() || !user.getName().equals(name)) {
			user.setName(name);
			return true;
		}
		return false;
	}
	
	private boolean _identity(Request request, User user) {
		String identity = request.getParam(Params.IDENTITY);
		if (null == user.getIdentity() || !user.getIdentity().equals(identity)) {
			user.setIdentity(identity);
			return true;
		}
		return false;
	}
	
	private boolean _avatar(Request request, User user) {
		String avatar = request.getParam(Params.AVATAR);
		if (null == user.getAvatar() || !user.getAvatar().equals(avatar)) {
			user.setAvatar(avatar);
			return true;
		}
		return false;
	}
	
	private boolean _identityFace(Request request, User user) {
		String identityFace = request.getParam(Params.IDENTITY_FACE);
		if (null == user.getIdentityFace() || !user.getIdentityFace().equals(identityFace)) {
			user.setIdentityFace(identityFace);
			return true;
		}
		return false;
	}
	
	private boolean _identityBack(Request request, User user) {
		String identityBack = request.getParam(Params.IDENTITY_BACK);
		if (null == user.getIdentityBack() || !user.getIdentityBack().equals(identityBack)) {
			user.setIdentityBack(identityBack);
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
