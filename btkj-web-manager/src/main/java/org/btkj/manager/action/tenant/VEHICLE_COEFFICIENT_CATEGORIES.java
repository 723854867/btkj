package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.List;

import org.btkj.manager.action.TenantAction;
import org.btkj.manager.model.VehicleCoefficientCategoryInfo;
import org.btkj.pojo.enums.VehicleCoefficientCategory;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class VEHICLE_COEFFICIENT_CATEGORIES extends TenantAction {

	@Override
	protected Result<List<VehicleCoefficientCategoryInfo>> execute(Request request, EmployeeForm employeeForm) {
		List<VehicleCoefficientCategoryInfo> list = new ArrayList<VehicleCoefficientCategoryInfo>();
		for (VehicleCoefficientCategory category : VehicleCoefficientCategory.values())
			list.add(new VehicleCoefficientCategoryInfo(category));
		return Result.result(list);
	}
}
