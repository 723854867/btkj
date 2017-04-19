package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.message.Result;

public class TENANT_TIPS implements Action {

	@Resource
	private TenantService tenantService;
	
	@Override
	public Result<TenantTips> execute(Request request) {
		Tenant tenant = tenantService.getTenantById(request.getParam(Params.TID));
		if (null == tenant)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		return Result.result(new TenantTips(tenant));
	}
}
