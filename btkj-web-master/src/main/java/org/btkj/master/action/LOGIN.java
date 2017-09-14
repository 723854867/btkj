package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.api.CloudService;
import org.btkj.pojo.info.master.LoginInfo;
import org.btkj.web.util.Params;
import org.btkj.web.util.action.IAction;
import org.btkj.web.util.action.Request;
import org.rapid.util.common.message.Result;

public class LOGIN implements IAction {
	
	@Resource
	private CloudService cloudService;

	@Override
	public Result<LoginInfo> execute(Request request) {
		int id = request.getParam(Params.ID);
		String pwd = request.getParam(Params.PWD);
		return cloudService.login(id, pwd);
	}
}
