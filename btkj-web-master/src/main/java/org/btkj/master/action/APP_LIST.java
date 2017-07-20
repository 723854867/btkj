package org.btkj.master.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.AppPO;
import org.btkj.user.api.UserManageService;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class APP_LIST extends LoggedAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<List<AppPO>> execute(Request request, Administrator operator) {
		return Result.result(new ArrayList<AppPO>(userManageService.apps().values()));
	}
}
