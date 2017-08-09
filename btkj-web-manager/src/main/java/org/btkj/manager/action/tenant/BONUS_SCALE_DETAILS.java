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
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.Insurer;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.model.BonusScale;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
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
	protected Result<List<BonusScaleWaterInfo>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, BonusScaleDetailsParam param) {
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
