package org.btkj.master.action.vehicle.baotu;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.Insurer;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class INSURERS extends AdministratorAction {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<List<Insurer>> execute(Request request, Administrator operator) {
		return Result.result(configManageService.insurers());
	}
}
