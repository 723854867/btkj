package org.btkj.manager.action.user;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigManageService;
import org.btkj.manager.action.UserAction;
import org.btkj.manager.pojo.info.TenantsUserInfo;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.param.NilParam;
import org.btkj.user.api.EmployeeService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class TENANTS_USER extends UserAction<NilParam> {
	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private ConfigManageService configManageService;

	@Override
	protected Result<TenantsUserInfo> execute(App app, User user, NilParam param) {
		Set<String> pset = configManageService.modularsPossessed(user.getUid(), ModularType.USER);
		Map<Integer, EmployeeTip> employees = employeeService.employeeTips(app, user);
		Map<Integer, Set<String>> tsets = CollectionUtil.isEmpty(employees) ? null : configManageService.modularsPossessed(new HashSet<Integer>(employees.keySet()));
		return Result.result(new TenantsUserInfo(pset, tsets, employees));
	}
}
