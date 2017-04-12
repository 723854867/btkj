package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.AppMainPageInfo;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

/**
 * 首页信息
 * 
 * @author ahab
 */
public class MAIN_PAGE extends TenantAction {
	
	@Resource
	private UserService userService;
	
//	@Override
//	protected Result<?> execute(Request request, Credential credential) {
//		String token = request.getHeader(Params.TOKEN);
//		Result<AppMainPageInfo> result = Beans.userService.mainPage(credential.getApp(), credential.getTenant(), token);
//		if (!result.isSuccess())
//			return result;
//		ResultUtil.fillMainPageInfo(result.attach());
//		return result;
//	}

	@Override
	protected Result<?> execute(Request request, Client client, App app, Tenant tenant, User user) {
		Result<AppMainPageInfo> result = userService.mainPage(app, tenant, null);
		return null;
	}
}
