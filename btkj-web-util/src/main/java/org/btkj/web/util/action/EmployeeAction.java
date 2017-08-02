package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.UserService;
import org.btkj.user.pojo.model.EmployeeHolder;
import org.btkj.web.util.Params;
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
	protected EmployeeService employeeService;
	
	public EmployeeAction() {
		super();
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
			return execute(eh.getApp(), eh.getUser(), eh.getTenant(), eh.getEmployee(), param);
		} finally {
			if (userLock())
				userService.releaseUserLock(result.getDesc(), result.attach().getUser().getUid());
		}
	}
	
	protected abstract Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, PARAM param);
	
	protected boolean userLock() {
		return false;
	}
}