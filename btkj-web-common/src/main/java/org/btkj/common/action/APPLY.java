package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;


/**
 * 申请分为两类
 * 
 * @author ahab
 */
public class APPLY extends UserAction {
	
	@Resource
	private TenantService tenantService;
	
	@Override
	protected Result<?> execute(Request request, App app, Client client, User user) {
		int employeeId = request.getParam(Params.EMPLOYEE_ID);
		if (null == user.getIdentity() || null == user.getIdentity())
			return Result.result(BtkjCode.USER_DATA_INCOMPLETE);
		return tenantService.apply(user, employeeId);
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
	
	@Override
	protected Client client(Request request) {
		return Client.APP;
	}
}
