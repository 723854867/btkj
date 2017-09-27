package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.param.vehicle.TenantInsurerEditParam;
import org.btkj.user.api.TenantService;
import org.btkj.vehicle.api.VehicleService;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class TENANT_INSURER_EDIT extends AdminAction<TenantInsurerEditParam> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private VehicleService vehicleService;

	@Override
	protected Result<Void> execute(Admin admin, TenantInsurerEditParam param) {
		Tenant tenant = tenantService.tenant(param.getTid());
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		return vehicleService.insurerEdit(param);
	}
}
