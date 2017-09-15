package org.btkj.common.action.tenant;

import java.util.ArrayList;
import java.util.List;

import org.btkj.common.pojo.info.PairInfo;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class USED_TYPES extends EmployeeAction<EmployeeParam> {

	@Override
	protected Result<List<PairInfo>> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		List<PairInfo> list = new ArrayList<PairInfo>();
		for (VehicleUsedType usedType : VehicleUsedType.supportTypes())
			list.add(new PairInfo(usedType));
		return Result.result(list);
	}
}
