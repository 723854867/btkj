package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.pojo.po.Administrator;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.AppInfo;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class APP_LIST extends LoggedAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<List<AppInfo>> execute(Request request, Administrator operator) {
		return Result.result(userManageService.apps());
	}
}