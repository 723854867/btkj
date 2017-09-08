package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.info.BonusScaleInfo;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.BonusScale;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.user.api.UserManageService;
import org.rapid.util.common.message.Result;

public class BONUS_SCALE_AUDITS extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private UserManageService userManageService;

	@Override
	protected Result<Pager<BonusScaleInfo>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		Pager<BonusScale> pager = userManageService.bonusScales(tenant.getTid(), param);
		Set<Integer> set = new HashSet<Integer>();
		for (BonusScale scale : pager.getList())
			set.add(scale.getEmployeeId());
		Map<Integer, EmployeeTip> tips = employeeService.employeeTips(set);
		List<BonusScaleInfo> list = new ArrayList<BonusScaleInfo>(pager.getList().size());
		for (BonusScale scale : pager.getList()) {
			EmployeeTip tip = tips.get(scale.getEmployeeId());
			list.add(new BonusScaleInfo(tip, scale));
		}
		return Result.result(new Pager<BonusScaleInfo>(pager.getTotal(), list));
	}
}
