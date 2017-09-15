package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.info.BonusScaleWaterInfo;
import org.btkj.manager.pojo.param.BonusScaleDetailsParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.VehiclePolicy;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.BonusScale;
import org.btkj.user.api.UserManageService;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class BONUS_SCALE_DETAILS extends EmployeeAction<BonusScaleDetailsParam> {
	
	@Resource
	private ConfigService configService;
	@Resource
	private UserManageService userManageService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<BonusScaleWaterInfo>> execute(App app, User user, Tenant tenant, Employee employee, BonusScaleDetailsParam param) {
		BonusScale bonusScale = userManageService.bonusScale(param.getKey());
		if (null == bonusScale)
			return BtkjConsts.RESULT.BONUS_SCALE_NOT_EXIST;
		if (bonusScale.getTid() != tenant.getTid())
			return Consts.RESULT.FORBID;
		Map<String, VehiclePolicy> policies = CollectionUtil.isEmpty(bonusScale.getPolicies()) ? null : vehicleManageService.policies(bonusScale.getPolicies());
		if (CollectionUtil.isEmpty(policies))
			return Result.result(Collections.EMPTY_LIST);
		Set<Integer> set = new HashSet<Integer>();
		Set<Integer> set1 = new HashSet<Integer>();
		for (VehiclePolicy policy : policies.values()) {
			set.add(policy.getSalesmanId());
			set1.add(policy.getInsurerId());
		}
		List<BonusScaleWaterInfo> details = new ArrayList<BonusScaleWaterInfo>();
		Map<Integer, EmployeeTip> employees = employeeService.employeeTips(set);
		Map<Integer, Insurer> insurers = configService.insurers(set1);
		for (VehiclePolicy policy : policies.values())
			details.add(new BonusScaleWaterInfo(employees.get(policy.getSalesmanId()), policy, insurers.get(policy.getInsurerId())));
		return Result.result(details);
	}

}
