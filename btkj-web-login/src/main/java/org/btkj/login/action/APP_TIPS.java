package org.btkj.login.action;

import org.btkj.pojo.bo.indentity.App;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.vo.AppTips;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AppAction;
import org.rapid.util.common.message.Result;

public class APP_TIPS extends AppAction {
	
	@Override
	protected Result<?> execute(Request request, App app) {
		return Result.result(new AppTips(app));
	}
	
	@Override
	public Client client(Request request) {
		return Client.TENANT_MANAGER;
	}
}
