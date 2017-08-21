package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.param.AuthorizeParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.AppPO;
import org.btkj.user.api.AppService;
import org.rapid.util.common.message.Result;

/**
 * 给平台授权
 * 
 * @author ahab
 */
public class AUTHORIZE_APP extends AdminAction<AuthorizeParam> {
	
	@Resource
	private AppService appService;
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(Admin admin, AuthorizeParam param) {
		AppPO app = appService.app(param.getTarId());
		if (null == app)
			return BtkjConsts.RESULT.APP_NOT_EXIST;
		return configManageService.authorizeApp(param.getTarId(), param.getModulars());
	}
}
