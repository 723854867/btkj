package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.App;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class APP_EDIT extends AdministratorAction {
	@Resource
	private AppService appService;

	@Override
	protected Result<?> execute(Request request, Administrator administrator) {
		App app = request.getParam(Params.APP_INFO);
		return appService.appEdit(app);
	}
}
