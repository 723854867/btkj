package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.model.identity.User;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OldUserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class USER_EDIT extends OldUserAction {
	
	@Resource
	private UserService userService;
	
	@Override
	protected Result<?> execute(Request request, User user) {
		UserPO po = user.getEntity();
		boolean nameChanged = _name(request, po);
		boolean avatarChanged = _avatar(request, po);
		boolean identityChanged = _identity(request, po);
		boolean identityFaceChanged = _identityFace(request, po);
		boolean identityBackChanged = _identityBack(request, po);
		if (!nameChanged && !avatarChanged && !identityChanged && !identityFaceChanged && !identityBackChanged)
			return Consts.RESULT.OK;
		return userService.update(user.getEntity());
	}
	
	private boolean _name(Request request, UserPO user) {
		String name = request.getParam(Params.NAME);
		if (null == user.getName() || !user.getName().equals(name)) {
			user.setName(name);
			return true;
		}
		return false;
	}
	
	private boolean _identity(Request request, UserPO user) {
		String identity = request.getParam(Params.IDENTITY);
		if (null == user.getIdentity() || !user.getIdentity().equals(identity)) {
			user.setIdentity(identity);
			return true;
		}
		return false;
	}
	
	private boolean _avatar(Request request, UserPO user) {
		String avatar = request.getParam(Params.AVATAR);
		if (null == user.getAvatar() || !user.getAvatar().equals(avatar)) {
			user.setAvatar(avatar);
			return true;
		}
		return false;
	}
	
	private boolean _identityFace(Request request, UserPO user) {
		String identityFace = request.getParam(Params.IDENTITY_FACE);
		if (null == user.getIdentityFace() || !user.getIdentityFace().equals(identityFace)) {
			user.setIdentityFace(identityFace);
			return true;
		}
		return false;
	}
	
	private boolean _identityBack(Request request, UserPO user) {
		String identityBack = request.getParam(Params.IDENTITY_BACK);
		if (null == user.getIdentityBack() || !user.getIdentityBack().equals(identityBack)) {
			user.setIdentityBack(identityBack);
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean userIntegrityVerify(User user) {
		return true;
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
