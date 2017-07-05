package org.btkj.master.action.vehicle.bihu;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuConfigService;
import org.btkj.bihu.vehicle.pojo.entity.BiHuInsurer;
import org.btkj.pojo.entity.Administrator;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class BI_HU_INSURERS extends AdministratorAction {
	
	@Resource
	private BiHuConfigService biHuConfigService;

	@Override
	protected Result<List<BiHuInsurer>> execute(Request request, Administrator operator) {
		return Result.result(biHuConfigService.insurers());
	}
}
