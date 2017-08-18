package org.btkj.login.action.app;

import org.btkj.login.pojo.info.AppInfo;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.identity.App;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.OldAppAction;
import org.rapid.util.common.message.Result;

public class APP_TIPS extends OldAppAction {
	
	@Override
	protected Result<AppInfo> execute(Request request, App app) {
		return Result.result(new AppInfo(app));
	}
	
	@Override
	public Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
