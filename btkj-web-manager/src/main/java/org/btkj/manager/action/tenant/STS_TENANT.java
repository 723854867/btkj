package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.courier.api.AliyunService;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.courier.StsInfo;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class STS_TENANT extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private AliyunService aliyunService;

	@Override
	protected Result<StsInfo> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		StsInfo stsInfo = aliyunService.assumeRole(app, tenant);
		return null;
	}
}
