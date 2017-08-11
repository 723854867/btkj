package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.api.MasterService;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.message.Result;

public class ADMINS extends AdminAction<Param> {
	
	@Resource
	private MasterService masterService;

	@Override
	protected Result<Pager<Administrator>> execute(Administrator admin, Param param) {
		return masterService.admins(param);
	}
}
