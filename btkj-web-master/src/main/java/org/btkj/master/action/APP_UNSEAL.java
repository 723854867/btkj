package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.AppService;
import org.rapid.util.common.message.Result;

public class APP_UNSEAL extends AdminAction<IdParam> {
	
	@Resource
	private AppService appService;

	@Override
	protected Result<Void> execute(Admin admin, IdParam param) {
		return appService.unseal(param.getId());
	}
}
