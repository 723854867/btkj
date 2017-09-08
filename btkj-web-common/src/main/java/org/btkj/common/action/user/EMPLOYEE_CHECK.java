package org.btkj.common.action.user;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.param.IdParam;
import org.btkj.user.api.EmployeeService;
import org.btkj.web.util.action.UserAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.StringUtil;

public class EMPLOYEE_CHECK extends UserAction<IdParam> {
	
	@Resource
	private EmployeeService employeeService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, IdParam param) {
		Result<Void> result = employeeService.tenantJoinCheck(user.getUid(), param.getId());
		if (!result.isSuccess())
			return result;
		if (!StringUtil.hasText(user.getName(), user.getIdentity())) 
			return BtkjConsts.RESULT.USER_DATA_INCOMPLETE;
		return result;
	}
}
