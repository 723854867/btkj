package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.api.MasterService;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.Param;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class ADMINS extends AdminAction<Param> {
	
	@Resource
	private MasterService masterService;

	@Override
	protected Result<Pager<Admin>> execute(Admin admin, Param param) {
		return masterService.admins(param);
	}
}
