package org.btkj.common.action.vehicle;

import java.util.ArrayList;
import java.util.List;

import org.btkj.common.pojo.info.PairInfo;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.VehicleUsedType;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;

public class USED_TYPES extends TenantAction {

	@Override
	protected Result<List<PairInfo>> execute(Request request, Client client, EmployeeForm employeeForm) {
		List<PairInfo> list = new ArrayList<PairInfo>();
		for (VehicleUsedType usedType : VehicleUsedType.supportTypes())
			list.add(new PairInfo(usedType));
		return Result.result(list);
	}
}
