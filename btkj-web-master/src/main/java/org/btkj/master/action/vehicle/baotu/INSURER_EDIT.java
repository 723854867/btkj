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
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.NumberUtil;

public class INSURER_EDIT extends AdministratorAction {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			int id = request.getParam(Params.ID);
			if (!NumberUtil.isPowerOfTwo(id))
				throw ConstConvertFailureException.errorConstException(Params.ID);
			return configManageService.insurerAdd(id, request.getParam(Params.NAME), request.getParam(Params.ICON), 
					request.getOptionalParam(Params.AGREE), request.getOptionalParam(Params.KEY));
		case UPDATE:
			return configManageService.insurerUpdate(request.getParam(Params.ID), request.getParam(Params.NAME), 
					request.getParam(Params.ICON), request.getOptionalParam(Params.AGREE), request.getOptionalParam(Params.KEY));
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
