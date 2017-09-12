package org.btkj.login.action.app;

import org.btkj.login.pojo.info.AppInfo;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.AppParam;
import org.btkj.web.util.action.AppAction;
import org.rapid.util.common.message.Result;

public class APP_TIPS extends AppAction<AppParam> {
	
	@Override
	protected Result<AppInfo> execute(AppPO app, AppParam param) {
		return Result.result(new AppInfo(app));
	}
	
	@Override
	public Client client() {
		return Client.TENANT_MANAGER;
	}
}
