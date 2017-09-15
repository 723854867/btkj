package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.info.user.ApplyPagingInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class APPLY_LIST extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<ApplyPagingInfo>> execute(App app, User user, Tenant tenant, Employee employee, EmployeeParam param) {
		return userManageService.applies(tenant.getTid(), param.getPage(), param.getPageSize());
	}
}
