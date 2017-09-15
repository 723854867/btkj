package org.btkj.common.action.tenant;

import java.util.ArrayList;
import java.util.List;

import org.btkj.common.pojo.info.PairInfo;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

public class USED_TYPES extends EmployeeAction<EmployeeParam> {

	@Override
	protected Result<List<PairInfo>> execute(App app, User user, Tenant tenant, Employee employee, EmployeeParam param) {
		List<PairInfo> list = new ArrayList<PairInfo>();
		for (VehicleUsedType usedType : VehicleUsedType.supportTypes())
			list.add(new PairInfo(usedType));
		return Result.result(list);
	}
}
