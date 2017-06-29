package org.btkj.manager.action.tenant.bonus;

import java.util.ArrayList;
import java.util.List;

import org.btkj.manager.action.TenantAction;
import org.btkj.manager.pojo.VehicleCoefficientCategories;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

/**
 * 车险系数大类
 * 
 * @author ahab
 */
public class VEHICLE_COEFFICIENT_CATEGORIES extends TenantAction {
	
	@Override
	protected Result<List<VehicleCoefficientCategories>> execute(Request request, EmployeeForm employeeForm) {
		List<VehicleCoefficientCategories> list = new ArrayList<VehicleCoefficientCategories>();
		for (CoefficientType coefficientType : CoefficientType.values()) {
			VehicleCoefficientCategories typeInfo = new VehicleCoefficientCategories();
			typeInfo.addCoefficientInfo(coefficientType);
		}
		return Result.result(list);
	}
}
