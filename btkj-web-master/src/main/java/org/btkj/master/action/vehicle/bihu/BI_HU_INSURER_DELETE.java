package org.btkj.master.action.vehicle.bihu;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuConfigService;
import org.btkj.pojo.entity.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;

public class BI_HU_INSURER_DELETE extends AdministratorAction {
	
	@Resource
	private BiHuConfigService biHuConfigService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		biHuConfigService.deleteBiHuInsurer(request.getParam(Params.ID));
		return Consts.RESULT.OK;
	}
}
