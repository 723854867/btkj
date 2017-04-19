package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.info.tips.AppTips;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

public class APP_TIPS implements Action {
	
	@Resource
	private AppService appService;

	@Override
	public Result<AppTips> execute(Request request) {
		App app = appService.getAppById(request.getParam(Params.APP_ID));
		if (null == app)
			return Result.result(BtkjCode.APP_NOT_EXIST);
		return Result.result(new AppTips(app));
	}
}
