package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.param.vehicle.TenantSetParam;
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
	protected Result<Void> execute(Admin admin, TenantSetParam param) {
		TenantPO tenant = tenantService.tenant(param.getTid());
		if (null == tenant)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		tenant.setJianJieId(param.getJianJieId());
		tenant.setBiHuAgent(param.getBiHuAgent());
		tenant.setBiHuKey(param.getBiHuKey());
		tenant.setLeBaoBaUsername(param.getLeBaoBaUsername());
		tenant.setLeBaoBaPassword(param.getLeBaoBaPassword());
		tenant.setUpdated(DateUtil.currentTime());
		userManageService.tenantUpdate(tenant);
		vehicleManageService.insurerEdit(param);
		return Consts.RESULT.OK;
	}
}
