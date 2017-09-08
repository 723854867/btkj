package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.master.Admin;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.param.vehicle.JianJieInsurerEditParam;
import org.btkj.user.api.TenantService;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class JIAN_JIE_INSURER_EDIT extends AdminAction<JianJieInsurerEditParam> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private VehicleManageService vehicleManageService;
	
	public JIAN_JIE_INSURER_EDIT() {
		super(CrudType.DELETE, CrudType.CREATE);
	}

	@Override
	protected Result<?> execute(Admin admin, JianJieInsurerEditParam param) {
		if (param.getCrudType() == CrudType.CREATE) {
			TenantPO tenant = tenantService.tenant(param.getTid());
			if (null == tenant)
				return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		}
		return vehicleManageService.jianJieInsurerEdit(param);
	}
}
