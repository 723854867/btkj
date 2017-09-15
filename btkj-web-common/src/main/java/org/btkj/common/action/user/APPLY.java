package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;


/**
 * 申请分为两类: app 端申请加入代理公司， h5 推广加入代理公司
 * 
 * @author ahab
 */
public class APPLY extends UserAction<IdParam> {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private CourierService courierService;
	@Resource
	private EmployeeService employeeService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, IdParam param) {
		EmployeeTip chief = employeeService.employeeTip(param.getId());
		if (null == chief)
			return BtkjConsts.RESULT.EMPLOYEE_NOT_EXIST;
		if (chief.getAppId() != user.getAppId())
			return Consts.RESULT.FORBID;
		return tenantService.apply(user, chief);
	}
	
	@Override
	protected boolean userLock() {
		return true;
	}
}
