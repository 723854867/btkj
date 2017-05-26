package org.btkj.lebaoba.vehicle.api;

import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.model.EmployeeForm;
import org.rapid.util.common.message.Result;

/**
 * 乐保吧车险
 * 
 * @author ahab
 */
public interface LeBaoBaVehicle {

	/**
	 * 报价/投保接口
	 */
	Result<Void> order(EmployeeForm employeeForm, int quoteMod, int insureMod, Renewal renewal);
}
