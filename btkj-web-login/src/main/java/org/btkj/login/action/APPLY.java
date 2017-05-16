package org.btkj.login.action;

import javax.annotation.Resource;

import org.btkj.courier.api.CourierService;
import org.btkj.pojo.BtkjCode;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.model.CaptchaVerifier;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.UserModel;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.ParamsUtil;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.Action;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;


/**
 * 申请分为两类: app 端申请加入代理公司， h5 推广加入代理公司
 * 
 * @author ahab
 */
public class APPLY implements Action {
	
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;
	@Resource
	private CourierService courierService;
	@Resource
	private EmployeeService employeeService;
	
	@Override
	public Result<?> execute(Request request) {
		Client client = request.getParam(Params.CLIENT);
		switch (client) {
		case PC:
			return _pcApply(request);
		case APP:
			return _appApply(request);
		default:
			return Result.result(Code.FORBID);
		}
	}
	
	private Result<?> _appApply(Request request) {
		String token = request.getHeader(Params.TOKEN);
		Result<UserModel> result = userService.lockUserByToken(Client.APP, token);
		if (!result.isSuccess())
			return result;
		try {
			return tenantService.apply(
					result.attach().getUser(), 
					request.getParam(Params.EMPLOYEE_ID), 
					request.getParam(Params.NAME), 
					request.getParam(Params.IDENTITY),
					request.getParam(Params.IDENTITY_FACE),
					request.getParam(Params.IDENTITY_BACK));
		} finally {
			userService.releaseUserLock(result.getDesc(), result.attach().getUser().getUid());
		}
	}
	
	private Result<?> _pcApply(Request request) {
		int employeeId = request.getParam(Params.EMPLOYEE_ID);
		EmployeeForm chief = employeeService.getById(employeeId);
		if (null == chief)
			return Result.result(BtkjCode.EMPLOYEE_NOT_EXIST);
		String name = request.getParam(Params.NAME);
		String identity = request.getParam(Params.IDENTITY);
		CaptchaVerifier verifier = ParamsUtil.captchaVerifier(request, chief.getApp().getId());
		if (courierService.captchaVerify(verifier).getCode() == -1)
			return Result.result(Code.CAPTCHA_ERROR);
		return tenantService.apply(
				verifier.getIdentity(), chief, name, identity, 
				request.getParam(Params.IDENTITY_FACE),
				request.getParam(Params.IDENTITY_BACK));
	}
}
