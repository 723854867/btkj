package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.model.identity.App;
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
public abstract class OldAppAction extends OldAction {
	
	@Resource
	protected AppService appService;

	@Override
	public Result<?> execute(Request request) {
		App app = appService.app(client(request), request.getParam(Params.APP_ID));
		return null == app ? Result.result(BtkjCode.APP_NOT_EXIST) : execute(request, app);
	}
	
	protected Result<?> execute(Request request, App app) {
		return Consts.RESULT.OK;
	}
}
