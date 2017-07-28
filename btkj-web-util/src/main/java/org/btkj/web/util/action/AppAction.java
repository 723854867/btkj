package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.bo.indentity.App;
import org.btkj.pojo.param.AppParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

/**
 * 需要传递 appId 参数来指定目的 app
 * 
 * @author ahab
 */
public abstract class AppAction<PARAM extends AppParam> extends Action<PARAM> {
	
	@Resource
	protected AppService appService;

	@Override
	public Result<?> execute(Request request) {
		if (null != clazz)
			return super.execute(request);
		else {
			App app = appService.app(client(request), request.getParam(Params.APP_ID));
			return null == app ? Result.result(BtkjCode.APP_NOT_EXIST) : execute(request, app);
		}
	}
	
	@Override
	protected Result<?> execute(PARAM param) {
		AppPO app = appService.app(param.getAppId());
		return execute(param, app);
	}
	
	protected Result<?> execute(PARAM param, AppPO app) {
		return Consts.RESULT.OK;
	}
	
	protected Result<?> execute(Request request, App app) {
		return Consts.RESULT.OK;
	}
}
