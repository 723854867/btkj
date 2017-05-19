package org.btkj.bihu.vehicle.api;

import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Renew;
import org.rapid.util.common.message.Result;

/**
 * 壁虎车险接口
 * 
 * @author ahab
 */
public interface BiHuVehicle {

	/**
	 * 获取续保信息
	 * 
	 * @param employeeForm
	 * @param license 车牌号
	 * @return
	 */
	Result<Renew> renewlInfo(EmployeeForm employeeForm, String license);
	
	/**
	 * 报价
	 */
	void quote(EmployeeForm employeeForm, String license);
}
