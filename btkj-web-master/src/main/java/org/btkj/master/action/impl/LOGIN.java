package org.btkj.master.action.impl;

import javax.annotation.Resource;

import org.btkj.master.service.realm.RoleService;
import org.btkj.pojo.info.LoginInfo;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;


public class LOGIN implements Action {
	
	@Resource
	private  RoleService roleService;

	@Override
	public Result<LoginInfo> execute(Request request) {
		int id = request.getParam(Params.ID);
		String pwd = request.getParam(Params.PWD);
		return roleService.login(id, pwd);
	}
}
