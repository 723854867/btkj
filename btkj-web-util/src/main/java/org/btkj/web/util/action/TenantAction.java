package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 租户接口：默认只能操作同一个代理公司的资源，如果可以跨代理公司操作，则可以重写{@link #checkEmployee(TenantPO, UserPO)} 方法
 * 
 * @author ahab
 */
public abstract class TenantAction extends UserAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private EmployeeService employeeService;
	
	@Override
	protected Result<?> execute(Request request, AppPO app, Client client, UserPO user) {
		int employeeId = request.getParam(Params.EMPLOYEE_ID);
		EmployeeForm ef = employeeService.getById(employeeId);
		if (null == ef)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		if (ef.getUser().getUid() != user.getUid())
			return Result.result(Code.FORBID);
		return execute(request, client, ef);
	}
	
	protected abstract Result<?> execute(Request request, Client client, EmployeeForm ef);
}
