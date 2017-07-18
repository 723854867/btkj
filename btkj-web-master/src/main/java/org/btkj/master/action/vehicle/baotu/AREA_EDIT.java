package org.btkj.master.action.vehicle.baotu;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.pojo.po.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class AREA_EDIT extends AdministratorAction {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			return configManageService.areaAdd(request.getParam(Params.REGION), request.getParam(Params.NUM), request.getOptionalParam(Params.IDX));
		case UPDATE:
			return configManageService.areaUpdate(request.getParam(Params.REGION), request.getParam(Params.NUM), request.getOptionalParam(Params.IDX));
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
