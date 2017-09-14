package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.TenantInfo;
import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.action.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

public class TENANT_INFO extends TenantAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<TenantInfo> execute(Request request, Employee employee) {
		TenantPO po = tenantService.tenant(employee.getTid());
		if (null == po)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		Region region = configService.region(employee.getTenant().getRegion());
		return Result.result(new TenantInfo(employee, region));
	}
}
