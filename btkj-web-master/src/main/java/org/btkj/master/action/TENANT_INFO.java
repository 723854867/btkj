package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.master.pojo.info.TenantInfo;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.AppService;
import org.btkj.user.api.TenantService;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.web.util.action.AdminAction;
import org.rapid.util.common.message.Result;

public class TENANT_INFO extends AdminAction<IdParam> {
	
	@Resource
	private AppService appService;
	@Resource
	private ConfigService configService;
	@Resource
	private TenantService tenantService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<TenantInfo> execute(Admin admin, IdParam param) {
		Tenant tenant = tenantService.tenant(param.getId());
		if (null == tenant)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		Region region = configService.region(tenant.getRegion());
		App app = appService.app(tenant.getAppId());
		return Result.result(new TenantInfo(tenant, app, region));
	}
}
