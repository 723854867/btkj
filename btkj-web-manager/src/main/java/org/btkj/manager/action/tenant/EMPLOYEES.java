package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.user.EmployeePagingInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.user.EmployeesParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class EMPLOYEES extends EmployeeAction<EmployeesParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<EmployeePagingInfo>> execute(App app, User user, Tenant tenant, Employee employee, EmployeesParam param) {
		param.setAppId(app.getId());
		param.setTid(tenant.getTid());
		return userManageService.employees(param);
	}
}
