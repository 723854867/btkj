package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 商户接口：首先得是用户才可以
 * 
 * @author ahab
 */
public abstract class TenantAction<PARAM extends EmployeeParam> extends UserAction<PARAM> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private EmployeeService employeeService;
	
	@Override
	protected Result<?> execute(Request request, User user) {
		if (null != clazz)
			return super.execute(request);
		else {
			Employee employee = employeeService.employee(request.getParam(Params.EMPLOYEE_ID));
			if (null == employee)
				return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
			if (employee.getUid() != user.getUid())
				return Result.result(Code.FORBID);
			return execute(request, employee);
		}
	}
	
	protected Result<?> execute(Request request, Employee employee) {
		return Consts.RESULT.OK;
	}
}
