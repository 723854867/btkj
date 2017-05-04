package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public abstract class AppAction implements Action {
	
	@Resource
	private AppService appService;

	@Override
	public Result<?> execute(Request request) {
		Client client = client(request);
		App app = appService.getAppById(request.getParam(Params.APP_ID));
		if (null == app)
			return Result.result(BtkjCode.APP_NOT_EXIST);
		return execute(request, client, app);
	}
	
	protected abstract Result<?> execute(Request request, Client client, App app);
	
	protected Client client(Request request) {
		return request.getParam(Params.CLIENT);
	}
}
