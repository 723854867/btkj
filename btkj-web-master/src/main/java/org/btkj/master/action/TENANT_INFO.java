package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.LoggedAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.po.TenantPO;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class TENANT_INFO extends LoggedAction {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<?> execute(Request request, Administrator administrator) {
		int tid = request.getParam(Params.TID);
		TenantPO tenant = tenantService.tenant(tid);
		if (null == tenant)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		return Result.result(tenant);
	}
}
