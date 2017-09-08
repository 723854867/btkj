package org.btkj.master.action;

import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.AdminAction;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.param.NilParam;
import org.rapid.util.common.message.Result;

public class MODULARS_POSSESSED extends AdminAction<NilParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Set<String>> execute(Admin admin, NilParam param) {
		return Result.result(configManageService.modularsPossessed(admin.getId(), ModularType.ADMIN));
	}
}
