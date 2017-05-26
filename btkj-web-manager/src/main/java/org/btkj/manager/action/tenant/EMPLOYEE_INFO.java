package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.info.EmployeeInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_INFO extends TenantAction {
	
	@Resource
	private EmployeeService employeeService;

	@Override
	protected Result<?> execute(Request request, EmployeeForm employeeForm) {
		int id = request.getParam(Params.ID);
		String name = request.getParam(Params.NAME);
		EmployeeInfo info = employeeService.employeeInfo(id);
		if (null == info)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		info.setPname(name);
		return Result.result(info);
	}
}
