package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.LoggedAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class API_EDIT extends LoggedAction {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<?> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			return configManageService.apiAdd(request.getParam(Params.KEY), request.getParam(Params.NAME), request.getParam(Params.NUM));
		case UPDATE:
			return configManageService.apiUpdate(request.getParam(Params.KEY), request.getParam(Params.NAME), request.getParam(Params.NUM));
		case DELETE:
			configManageService.apiDelete(request.getParam(Params.KEY));
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
