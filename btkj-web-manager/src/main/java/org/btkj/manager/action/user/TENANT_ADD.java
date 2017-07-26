package org.btkj.manager.action.user;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.courier.api.CourierService;
import org.btkj.courier.api.JianJieService;
import org.btkj.manager.action.UserAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.po.UserPO;
import org.btkj.user.api.AppService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;
import org.rapid.util.lang.DateUtil;

public class TENANT_ADD extends UserAction {
	
	@Resource
	private AppService appService;
	@Resource
	private UserService userService;
	@Resource
	private TenantService tenantService;
	@Resource
	private ConfigService configService;
	@Resource
	private CourierService courierService;
	@Resource
	private JianJieService jianJieService;

	@Override
	protected Result<?> execute(Request request, User user) {
		String tname = request.getParam(Params.TNAME);
		String license = request.getParam(Params.IDENTITY);
		String licenseImage = request.getParam(Params.IDENTITY_FACE);
		String mobile = request.getParam(Params.MOBILE);
		String servicePhone = request.getParam(Params.SERVICE_PHONE);
		int expire = request.getParam(Params.END_TIME);
		if (expire <= DateUtil.currentTime())
			throw ConstConvertFailureException.errorConstException(Params.END_TIME);
		Result<UserPO> ru = userService.userLockByMobile(user.getAppId(), mobile);
		if (!ru.isSuccess())
			return Consts.RESULT.USER_NOT_EXIST;
		UserPO target = ru.attach();
		try {
			if (null == target.getName())				// 资料不齐的用户不能作为商户顶级雇员
				return BtkjConsts.RESULT.USER_DATA_INCOMPLETE;
			Result<Employee> result = tenantService.tenantAdd(user.getAppId(), target.getUid(), tname, license, licenseImage, servicePhone, expire);
			if (result.isSuccess())
				jianJieService.addEmployee(target.getName() + "-" + tname, target.getIdentity(), result.attach().getId());
			return Consts.RESULT.OK;
		} finally {
			userService.releaseUserLock(ru.getDesc(), target.getUid());
		}
	}
}
