package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;


/**
 * 申请分为两类: app 端申请加入代理公司， h5 推广加入代理公司
 * 
 * @author ahab
 */
public class APPLY extends UserAction {
	
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;
	@Resource
	private CourierService courierService;
	@Resource
	private EmployeeService employeeService;
	
	@Override
	protected Result<?> execute(Request request, AppPO app, Client client, UserPO user) {
		int employeeId = request.getParam(Params.EMPLOYEE_ID);
		EmployeeForm chief = employeeService.getById(employeeId);
		if (null == chief)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (null == user.getName())
			return BtkjConsts.RESULT.USER_DATA_INCOMPLETE;
		return tenantService.apply(user, chief);
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
