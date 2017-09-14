package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.info.user.AppInfo;
import org.btkj.pojo.param.NilParam;
import org.btkj.user.api.UserManageService;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class APPS extends AdminAction<NilParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<List<AppInfo>> execute(Admin admin, NilParam param) {
		return Result.result(userManageService.apps());
	}
}
