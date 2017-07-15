package org.btkj.master.action.vehicle.baotu;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.info.AreaInfo;
import org.btkj.pojo.entity.Administrator;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class AREAS extends AdministratorAction {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<AreaInfo>> execute(Request request, Administrator operator) {
		return Result.result(configManageService.areas());
	}
}
