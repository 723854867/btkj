package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Administrator;
import org.btkj.pojo.entity.App;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class APP_INFO extends AdministratorAction {
	@Resource
	private AppService appService;

	@Override
	protected Result<?> execute(Request request, Administrator administrator) {
		int id = request.getParam(Params.ID);
		App app = appService.getAppById(id);
		if (null == app)
			return Result.result(BtkjCode.APP_NOT_EXIST);
		return Result.result(app);
	}
}
