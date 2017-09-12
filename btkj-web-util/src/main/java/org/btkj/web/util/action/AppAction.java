package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.param.AppParam;
import org.btkj.user.api.AppService;
import org.rapid.util.common.message.Result;

public abstract class AppAction<PARAM extends AppParam> extends Action<PARAM> {
	
	@Resource
	private AppService appService;

	@Override
	protected Result<?> execute(PARAM param) {
		AppPO app = appService.app(param.getAppId());
		if (null == app)
			return BtkjConsts.RESULT.APP_NOT_EXIST;
		return execute(app, param);
	}
	
	protected abstract Result<?> execute(AppPO app, PARAM param);
}
