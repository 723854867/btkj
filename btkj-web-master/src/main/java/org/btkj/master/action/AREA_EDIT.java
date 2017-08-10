package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.master.LoggedAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.BtkjConsts;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class AREA_EDIT extends LoggedAction {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		int renewalPeriod = request.getParam(Params.NUM);
		renewalPeriod = Math.max(BtkjConsts.LIMITS.MIN_RENEWAL_PERIOD, renewalPeriod);
		renewalPeriod = Math.min(BtkjConsts.LIMITS.MAX_RENEWAL_PERIOD, renewalPeriod);
		switch (crudType) {
		case CREATE:
			return configManageService.areaAdd(request.getParam(Params.REGION), renewalPeriod, request.getOptionalParam(Params.IDX), request.getOptionalParam(Params.PRICE_NO_TAX));
		case UPDATE:
			return configManageService.areaUpdate(request.getParam(Params.REGION), renewalPeriod, request.getOptionalParam(Params.IDX), request.getOptionalParam(Params.PRICE_NO_TAX));
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
