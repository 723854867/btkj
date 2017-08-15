package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.master.pojo.param.TenantSetParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.po.TenantPO;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserManageService;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;

public class TENANT_SET extends AdminAction<TenantSetParam> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private UserManageService userManageService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Void> execute(Administrator admin, TenantSetParam param) {
		TenantPO tenant = tenantService.tenant(param.getTid());
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		tenant.setJianJieId(param.getJianJieId());
		tenant.setBiHuAgent(param.getBihuAgent());
		tenant.setBiHuKey(param.getBihuKey());
		tenant.setUpdated(DateUtil.currentTime());
		userManageService.tenantUpdate(tenant);
		vehicleManageService.insurerEdit(tenant.getTid(), param.getInsurersDelete(), param.getInsurersUpdate(), param.getInsurersInsert());
		return Consts.RESULT.OK;
	}
}
