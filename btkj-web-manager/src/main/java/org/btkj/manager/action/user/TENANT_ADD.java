package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.user.api.TenantService;
import org.btkj.user.pojo.param.TenantAddParam;
import org.rapid.util.common.message.Result;

public class TENANT_ADD extends UserAction<TenantAddParam> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private JianJieService jianJieService;

	@Override
	protected Result<EmployeeTip> execute(AppPO app, UserPO user, TenantAddParam param) {
		Result<EmployeeTip> result = tenantService.tenantAdd(app.getId(), param);
		if (result.isSuccess())
			jianJieService.addEmployee(result.attach().getName() + "-" + param.getTname(), result.attach().getIdentity(), result.attach().getId());
		return result;
	}
}
