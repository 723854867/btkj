package org.btkj.baotu.vehicle.api;

import java.util.Set;

import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.info.VehiclePolicyTips;
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
	Result<Void> order(EmployeeTip employee, Set<Insurer> quote, Set<Insurer> insure, VehiclePolicyTips tips);
}
