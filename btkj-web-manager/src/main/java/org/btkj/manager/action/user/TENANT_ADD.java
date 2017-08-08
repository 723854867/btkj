package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.user.api.TenantService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtil;

public class TENANT_ADD extends UserAction {
	
	@Resource
	private TenantService tenantService;
	@Resource
	private JianJieService jianJieService;

	@Override
	protected Result<?> execute(Request request, User user) {
		int expire = request.getParam(Params.END_TIME);
		if (expire <= DateUtil.currentTime())
			throw ConstConvertFailureException.errorConstException(Params.END_TIME);
		String contactMobile = request.getParam(Params.CONTACTS_MOBILE);
		String name = request.getParam(Params.NAME);
		String tname = request.getParam(Params.TNAME);
		String license = request.getParam(Params.IDENTITY);
		String licenseImage = request.getParam(Params.IDENTITY_FACE);
		String mobile = request.getParam(Params.MOBILE);
		String servicePhone = request.getParam(Params.SERVICE_PHONE);
		Result<Employee> result = tenantService.tenantAdd(user.getAppId(), mobile, name, contactMobile, tname, license, licenseImage, servicePhone, expire);
		if (result.isSuccess())
			jianJieService.addEmployee(result.attach().getName() + "-" + tname, result.attach().getUser().getIdentity(), result.attach().getId());
		return result;
	}
}
