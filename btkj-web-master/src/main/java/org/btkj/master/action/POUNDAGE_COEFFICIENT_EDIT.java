package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Admin;
import org.btkj.pojo.BtkjConsts;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.PoundageCoefficientEditParam;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

public class POUNDAGE_COEFFICIENT_EDIT extends AdminAction<PoundageCoefficientEditParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;
	
	public POUNDAGE_COEFFICIENT_EDIT() {
		super(CrudType.CREATE, CrudType.UPDATE, CrudType.DELETE);
	}
	
	@Override
	protected Result<?> execute(Admin admin, PoundageCoefficientEditParam param) {
		param.setTid(BtkjConsts.GLOBAL_TENANT_ID);
		return vehicleManageService.poundageCoefficientEdit(param);
	}
}
