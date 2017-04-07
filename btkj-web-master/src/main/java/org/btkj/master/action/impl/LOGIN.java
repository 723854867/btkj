package org.btkj.master.action.impl;

import org.btkj.master.action.MasterAction;
import org.btkj.pojo.info.LoginInfo;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class LOGIN extends MasterAction {

	@Override
	public Result<LoginInfo> execute(Request request) {
		int id = request.getParam(Params.ID);
		String pwd = request.getParam(Params.PWD);
		return roleService.login(id, pwd);
	}
}
