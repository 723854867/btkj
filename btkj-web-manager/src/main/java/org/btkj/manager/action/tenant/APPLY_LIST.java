package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class APPLY_LIST extends TenantAction {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<ApplyPagingInfo>> execute(Request request, EmployeeForm employeeForm) {
		int page = request.getOptionalParam(Params.PAGE);
		int pageSize = request.getOptionalParam(Params.PAGE_SIZE);
		return userManageService.applyPaging(employeeForm.getTenant().getTid(), page, pageSize);
	}
}
