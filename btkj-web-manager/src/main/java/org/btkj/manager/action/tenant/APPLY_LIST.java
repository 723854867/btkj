package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class APPLY_LIST extends TenantAction {
	
	@Resource
	private TenantService tenantService;

	@Override
	protected Result<Pager<ApplyInfo>> execute(Request request, EmployeeForm employeeForm) {
		int page = request.getOptionalParam(Params.PAGE);
		int pageSize = request.getOptionalParam(Params.PAGE_SIZE);
		return tenantService.applyList(employeeForm.getTenant().getTid(), page, pageSize);
	}
}
