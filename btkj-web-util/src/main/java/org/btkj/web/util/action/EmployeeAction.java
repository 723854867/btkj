package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.AppPO.Mod;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.EmployeeHolder;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.rapid.util.common.enums.CrudType;
import org.rapid.util.common.message.Result;

/**
 * 雇员接口
 * 
 * @author ahab
 *
 * @param <PARAM>
 */
public abstract class EmployeeAction<PARAM extends EmployeeParam> extends Action<PARAM> {
	
	@Resource
	protected UserService userService;
	@Resource
	protected ConfigService configService;
	@Resource
	protected EmployeeService employeeService;
	
	public EmployeeAction() {
		super();
	}
	
	public EmployeeAction(CrudType... crudTypes) {
		super(crudTypes);
	}

	@Override
	protected Result<?> execute(PARAM param) {
		Client client = client();
		String token = request().getHeader(Params.TOKEN);
		Result<EmployeeHolder> result = userLock() 
				? employeeService.employeeLockByToken(client, token, param.getEmployeeId()) 
						: employeeService.employeeByToken(client, token, param.getEmployeeId());
		if (!result.isSuccess())
			return result;
		try {
			EmployeeHolder eh = result.attach();
			AppPO app = eh.getApp();
			UserPO user = eh.getUser();
			TenantPO tenant = eh.getTenant();
			EmployeePO employee = eh.getEmployee();
			if (Mod.SEAL.satisfy(app.getMod()))
				return BtkjConsts.RESULT.APP_SEALED;
			if (org.btkj.pojo.entity.UserPO.Mod.SEAL.satisfy(user.getMod()))
				return BtkjConsts.RESULT.USER_SEALED;
			if (org.btkj.pojo.entity.TenantPO.Mod.SEAL.satisfy(tenant.getMod()))
				return BtkjConsts.RESULT.TENANT_SEALED;
			if (org.btkj.pojo.entity.EmployeePO.Mod.SEAL.satisfy(employee.getMod()))
				return BtkjConsts.RESULT.EMPLOYEE_SEALED;
			return execute(app, user, tenant, employee, param);
		} finally {
			if (userLock())
				userService.releaseUserLock(result.getDesc(), result.attach().getUser().getUid());
		}
	}
	
	protected abstract Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PARAM param);
	
	protected Client client() {
		return request().getParam(Params.CLIENT);
	}
	
	protected boolean userLock() {
		return false;
	}
}
