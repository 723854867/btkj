package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.pojo.info.BonusScaleDetail;
import org.btkj.manager.pojo.param.BonusScaleDetailsParam;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.VehicleBonusType;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.pojo.entity.LogExploit;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.model.BonusScale;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

public class BONUS_SCALE_DETAILS extends EmployeeAction<BonusScaleDetailsParam> {
	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private StatisticsService statisticsService;
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<List<BonusScaleDetail>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, BonusScaleDetailsParam param) {
		BonusScale bonusScale = userManageService.bonusScale(param.getKey());
		if (null == bonusScale)
			return BtkjConsts.RESULT.BONUS_SCALE_NOT_EXIST;
		if (bonusScale.getTid() != tenant.getTid())
			return Consts.RESULT.FORBID;
		Map<Long, LogExploit> exploits = CollectionUtil.isEmpty(bonusScale.getExploits()) ? null : statisticsService.exploits(bonusScale.getExploits());
		if (CollectionUtil.isEmpty(exploits))
			return Result.result(Collections.EMPTY_LIST);
		Set<Integer> set = new HashSet<Integer>();
		for (LogExploit exploit : exploits.values())
			set.add(exploit.getEmployeeId());
		List<BonusScaleDetail> details = new ArrayList<BonusScaleDetail>();
		Map<Integer, EmployeeTip> employees = employeeService.employeeTips(set);
		for (LogExploit exploit : exploits.values()) {
			EmployeeTip et = employees.get(exploit.getEmployeeId());
			VehicleBonusType bonusType = VehicleBonusType.match(exploit.getDetailType());
			details.add(new BonusScaleDetail(et, exploit, bonusType));
		}
		return Result.result(details);
	}

}
