package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
import org.btkj.user.api.AppService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 首页信息
 * 
 * @author ahab
 */
public class MAIN_PAGE implements Action {
	
	@Resource
	private AppService appService;
	
	@Override
	public Result<IMainPageInfo> execute(Request request) {
		Client client = request.getParam(Params.CLIENT);
		String token = request.getOptionalHeader(Params.TOKEN);
		if (null != token) {		// 非游客模式有两种首页：app 首页，pc端首页
			int tid = request.getOptionalParam(Params.TID);
			return appService.mainPage(client, token, tid);
		}
		// 游客模式的首页只能 app 调用
		if (client != Client.APP)
			return Result.result(Code.FORBID);
		return appService.mainPage(request.getParam(Params.APP_ID));
	}
}
