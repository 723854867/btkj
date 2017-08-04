package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.message.Result;

public class MODULARS extends AdminAction<Param> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<?> execute(Administrator admin, Param param) {
		return null;
	}
}
