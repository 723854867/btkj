package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.api.CloudService;
import org.btkj.master.pojo.info.LoginInfo;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;


public class LOGIN extends Action {
	
	@Resource
	private CloudService cloudService;

	@Override
	public Result<LoginInfo> execute(Request request) {
		int id = request.getParam(Params.ID);
		String pwd = request.getParam(Params.PWD);
		return cloudService.login(id, pwd);
	}
}
