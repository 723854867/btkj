package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.AppService;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class APP_UNSEAL extends AdminAction<IdParam> {
	
	@Resource
	private AppService appService;

	@Override
	protected Result<Void> execute(Admin admin, IdParam param) {
		return appService.unseal(param.getId());
	}
}
