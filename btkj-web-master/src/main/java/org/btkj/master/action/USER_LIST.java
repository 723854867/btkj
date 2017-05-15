package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.info.UserListInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.UserSearcher;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class USER_LIST extends AdministratorAction {
	@Resource
	private UserService userService;

	@Override
	protected Result<Pager<UserListInfo>> execute(Request request, Administrator administrator) {
		UserSearcher searcher = request.getParam(Params.USER_SEARCHER);
		return userService.userList(searcher);
	}
}
