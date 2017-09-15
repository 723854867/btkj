package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.common.pojo.param.UserEditParam;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.user.api.UserService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.StringUtil;

public class USER_EDIT extends UserAction<UserEditParam> {
	
	@Resource
	private UserService userService;
	
	@Override
	protected Result<Void> execute(App app, User user, UserEditParam param) {
		boolean change = false;
		if (StringUtil.hasText(param.getName()) && !param.getName().equals(user.getName())) {
			user.setName(param.getName());
			change = true;
		}
		if (StringUtil.hasText(param.getIdentity()) && !param.getIdentity().equals(user.getIdentity())) {
			user.setIdentity(param.getIdentity());
			change = true;
		}
		if (!change)
			return Consts.RESULT.OK;
		return userService.update(user);
	}
}
