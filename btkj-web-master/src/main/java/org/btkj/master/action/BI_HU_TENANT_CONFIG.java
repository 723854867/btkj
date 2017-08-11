package org.btkj.master.action;

import javax.annotation.Resource;

import org.btkj.bihu.vehicle.api.BiHuManageService;
import org.btkj.master.AdminAction;
import org.btkj.master.pojo.entity.Administrator;
import org.btkj.pojo.param.IdParam;
import org.rapid.util.common.message.Result;

public class BI_HU_TENANT_CONFIG extends AdminAction<IdParam> {
	
	@Resource
	private BiHuManageService biHuManageService;

	@Override
	protected Result<?> execute(Administrator admin, IdParam param) {
		return Result.result(biHuManageService.tenantConfig(param.getId()));
	}
}
