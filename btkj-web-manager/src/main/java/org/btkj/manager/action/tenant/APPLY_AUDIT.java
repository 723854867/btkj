package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.ApplyAuditParam;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class APPLY_AUDIT extends EmployeeAction<ApplyAuditParam> {
	
	@Resource
	private JianJieService jianJieService;
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, ApplyAuditParam param) {
		Result<EmployeeTip> result = userManageService.applyAudit(tenant.getTid(), param.getUid(), param.isReject());
		if (!param.isReject() && result.isSuccess()) {
			String tname = result.attach().getTname();
			String name = result.attach().getName();
			int employeeId = result.attach().getId();
			jianJieService.addEmployee(name + "-" + tname, result.attach().getIdentity(), employeeId);
		}
		return result;
	}
}
