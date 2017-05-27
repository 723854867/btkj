package org.btkj.baotu.vehicle.api;

import java.util.Set;

import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.model.EmployeeForm;
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
	Result<Void> order(EmployeeForm employeeForm, Set<Integer> quote, Set<Integer> insure, Renewal renewal);
}
