package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.rapid.util.common.message.Result;

public class APPLY_LIST extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<ApplyPagingInfo>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		return userManageService.applies(tenant.getTid(), param.getPage(), param.getPageSize());
	}
}
