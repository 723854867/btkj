package org.btkj.master.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.master.pojo.info.TenantInfo;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.AppService;
import org.btkj.user.api.TenantService;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
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
		TenantPO tenant = tenantService.tenant(param.getId());
		if (null == tenant)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		Region region = configService.region(tenant.getRegion());
		AppPO app = appService.app(tenant.getAppId());
		Map<String, TenantInsurer> insurers = vehicleManageService.insurers(tenant.getTid());
		Set<Integer> set = new HashSet<Integer>();
		for (TenantInsurer insurer : insurers.values())
			set.add(insurer.getInsurerId());
		return Result.result(new TenantInfo(tenant, app, region, insurers, configService.insurers(set)));
	}
}
