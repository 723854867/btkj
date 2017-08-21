package org.btkj.manager.action.tenant;

import java.util.Map;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.config.pojo.TarType;
import org.btkj.config.pojo.info.ModularDocument;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.message.Result;
import org.rapid.util.math.tree.Node;

public class MODULARS_EMPLOYEE extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<Map<Integer, ModularDocument>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		int tarId = employee.getId();
		TarType tarType = TarType.EMPLOYEE;
		if (employee.getLevel() == Node.ROOT_LAYER) {
			tarId = tenant.getTid();
			tarType = TarType.TENANT;
		}
		return Result.result(configManageService.modulars(tarId, tarType, ModularType.TENANT));
	}
}
