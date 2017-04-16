package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;

/**
 * 租户接口：默认只能操作同一个代理公司的资源，如果可以跨代理公司操作，则可以重写{@link #checkEmployee(Tenant, User)} 方法
 * 
 * @author ahab
 */
public abstract class TenantAction extends UserAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private EmployeeService employeeService;
	
	@Override
	protected Result<?> execute(Request request, App app, Client client, User user) {
		int tid = request.getParam(Params.TID);
		Tenant tenant = tenantService.getTenantById(tid);
		if (null == tenant)
			return Result.result(BtkjCode.TENANT_NOT_EXIST);
		if (tenant.getAppId() != app.getId() || !checkEmployee(tenant, user))
			return Result.result(Code.FORBID);
		return execute(request, client, app, tenant, user);
	}
	
	protected abstract Result<?> execute(Request request, Client client, App app, Tenant tenant, User user);
	
	protected boolean checkEmployee(Tenant tenant, User user) {
		Employee employee = employeeService.getByTidAndUid(tenant.getTid(), user.getUid());
		return null != employee;
	}
}
