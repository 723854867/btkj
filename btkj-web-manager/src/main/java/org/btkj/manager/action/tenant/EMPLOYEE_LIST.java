package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.info.EmployeeListInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.EmployeeSearcher;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_LIST extends TenantAction {
	
	@Resource
	private EmployeeService employeeService;

	@Override
	protected Result<Pager<EmployeeListInfo>> execute(Request request, EmployeeForm employeeForm) {
		EmployeeSearcher searcher = request.getParam(Params.EMPLOYEE_SEARCHER);
		searcher.setTid(employeeForm.getTenant().getTid());
		return employeeService.employees(searcher);
	}
}
