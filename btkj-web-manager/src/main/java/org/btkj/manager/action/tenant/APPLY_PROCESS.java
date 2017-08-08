package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class APPLY_PROCESS extends TenantAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private JianJieService jianJieService;

	@Override
	protected Result<?> execute(Request request, Employee employee) {
		boolean agree = request.getOptionalParam(Params.AGREE);
		Result<EmployeeTip> result = tenantService.applyProcess(employee.getTid(), request.getParam(Params.UID), agree);
		if (agree && result.isSuccess()) {
			String tname = result.attach().getTname();
			String name = result.attach().getName();
			int employeeId = result.attach().getId();
			jianJieService.addEmployee(name + "-" + tname, result.attach().getIdentity(), employeeId);
		}
		return result;
	}
}
