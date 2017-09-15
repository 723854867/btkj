package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.info.TenantInfo;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.message.Result;

public class TENANT_INFO extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private ConfigService configService;

	@Override
	protected Result<TenantInfo> execute(App app, User user, Tenant tenant, Employee employee, EmployeeParam param) {
		Region region = configService.region(tenant.getRegion());
		return Result.result(new TenantInfo(app, tenant, region));
	}
}
