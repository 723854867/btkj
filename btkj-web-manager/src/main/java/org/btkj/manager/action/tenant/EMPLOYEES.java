package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.user.EmployeePagingInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.user.EmployeesParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class EMPLOYEES extends EmployeeAction<EmployeesParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<EmployeePagingInfo>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeesParam param) {
		param.setAppId(app.getId());
		param.setTid(tenant.getTid());
		return userManageService.employees(param);
	}
}
