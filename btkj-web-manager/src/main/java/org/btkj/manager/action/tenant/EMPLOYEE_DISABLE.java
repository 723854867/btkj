package org.btkj.manager.action.tenant;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.EmoloyeeInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class EMPLOYEE_DISABLE extends TenantAction {
	
	@Resource
	private EmployeeService employeeService;

	@Override
	protected Result<Pager<EmoloyeeInfo>> execute(Request request, Client client, App app, Tenant tenant, User user) {
		int id = request.getParam(Params.ID);
		return employeeService.employeeDisable(id);
	}
	
}
