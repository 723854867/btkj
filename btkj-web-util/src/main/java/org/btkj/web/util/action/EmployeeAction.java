package org.btkj.web.util.action;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.UserService;
import org.btkj.user.pojo.model.EmployeeHolder;
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
			return execute(eh.getApp(), eh.getUser(), eh.getTenant(), eh.getEmployee(), param);
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
