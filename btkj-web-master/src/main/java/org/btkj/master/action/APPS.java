package org.btkj.master.action;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.AppInfo;
import org.rapid.util.common.message.Result;

public class APPS extends AdminAction<Param> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<List<AppInfo>> execute(Administrator admin, Param param) {
		return Result.result(userManageService.apps());
	}
}
