package org.btkj.baotu.vehicle.api;

import java.util.Set;

import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.po.Insurer;
import org.btkj.pojo.vo.VehiclePolicyTips;
import org.rapid.util.common.message.Result;

/**
 * 保途车险
 * 
 * @author ahab
 */
public interface BaoTuVehicle {

	/**
	 * 报价/投保接口
	 */
	Result<Void> order(EmployeeForm employeeForm, Set<Insurer> quote, Set<Insurer> insure, VehiclePolicyTips tips);
}
