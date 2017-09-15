package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.TenantInfo;
import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class TENANT_INFO extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private ConfigService configService;
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<TenantInfo> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		TenantPO po = tenantService.tenant(employee.getTid());
		if (null == po)
			return BtkjConsts.RESULT.TENANT_NOT_EXIST;
		Region region = configService.region(tenant.getRegion());
		return Result.result(new TenantInfo(app, tenant, region));
	}
}
