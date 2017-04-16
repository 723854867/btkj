package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
import org.btkj.user.api.AppService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
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
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;
	
	@Override
	public Result<IMainPageInfo> execute(Request request) {
		Client client = request.getParam(Params.CLIENT);
		String token = request.getOptionalHeader(Params.TOKEN);
		if (null != token) {		// 非游客模式有三种首页：app 首页，pc端首页，管理后台首页
			int tid = request.getOptionalParam(Params.TID);
			Tenant tenant = 0 == tid ? null : tenantService.getTenantById(tid);
			if ((client == Client.PC || client == Client.MANAGER) && null == tenant)
				return Result.result(Code.FORBID);
			Result<IMainPageInfo> result = userService.mainPage(client, token, tenant);
		}
		// 游客模式的首页
		App app = appService.getAppById(request.getParam(Params.APP_ID));
		if (null == app)
			return Result.result(BtkjCode.APP_NOT_EXIST);
//		ResultUtil.fillMainPageInfo(result.attach());
		return null;
	}
}
