package org.btkj.manager.action.tenant;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.rapid.util.common.message.Result;

public class MODULARS extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		return Result.result(configManageService.modulars(TarType.EMPLOYEE, tenant.getTid()));
	}
}
