package org.btkj.manager.action;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.pojo.info.ModularsInfo;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.param.NilParam;
import org.btkj.user.api.EmployeeService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class MODULARS_POSSESSED extends UserAction<NilParam> {
	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<ModularsInfo> execute(AppPO app, UserPO user, NilParam param) {
		Set<String> pset = configManageService.modularsPossessed(user.getUid(), ModularType.USER);
		Map<Integer, EmployeeTip> employees = employeeService.employeeTips(app, user);
		Map<Integer, Set<String>> tsets = CollectionUtil.isEmpty(employees) ? null : configManageService.modularsPossessed(employees.keySet());
		return Result.result(new ModularsInfo(pset, tsets, employees));
	}
}
