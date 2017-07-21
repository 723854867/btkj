package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuManageService;
import org.btkj.master.LoggedAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.TenantPO;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.common.message.Result;

public class BI_HU_TENANT_CONFIG_EDIT extends LoggedAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private BiHuManageService biHuManageService;

	@Override
	protected Result<Void> execute(Request request, Administrator operator) {
		CRUD_TYPE crudType = request.getParam(Params.CRUD_TYPE);
		switch (crudType) {
		case CREATE:
			int tid = request.getParam(Params.TID);
			TenantPO tenant = tenantService.tenant(tid);
			if (null == tenant)
				return BtkjConsts.RESULT.TENANT_NOT_EXIST;
			return biHuManageService.tenantConfigAdd(tid, request.getParam(Params.AGENT), request.getParam(Params.KEY));
		case UPDATE:
			return biHuManageService.tenantConfigUpdate(request.getParam(Params.TID), request.getParam(Params.AGENT), request.getParam(Params.KEY));
		case DELETE:
			biHuManageService.tenantConfigDelete(request.getParam(Params.TID));
			return Consts.RESULT.OK;
		default:
			return Consts.RESULT.FORBID;
		}
	}
}
