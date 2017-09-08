package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.info.TenantInfo;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.message.Result;

public class TENANT_INFO extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private ConfigService configService;

	@Override
	protected Result<TenantInfo> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		Region region = configService.region(tenant.getRegion());
		return Result.result(new TenantInfo(app, tenant, region));
	}
}
