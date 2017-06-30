package org.btkj.bihu.vehicle.api;

import java.util.Set;

import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.insur.vehicle.PolicyDetail;
import org.btkj.pojo.model.insur.vehicle.PolicySchema;
import org.rapid.util.common.message.Result;

/**
 * 壁虎车险接口
 * 
 * @author ahab
 */
public interface BiHuVehicle {

	/**
	 * 获取续保信息：通过车牌号
	 * 
	 * @param employeeForm
	 * @param license 车牌号
	 * @param name 车主姓名
	 * @return
	 */
	Result<Renewal> renewal(EmployeeForm employeeForm, String license);
	
	/**
	 * 获取续保信息：通过车架号和发动机号
	 * 
	 * @param employeeForm
	 * @param vin
	 * @param engine
	 * @param name
	 * @return
	 */
	Result<Renewal> renewal(EmployeeForm employeeForm, String vin, String engine);
	
	/**
	 * 报价/投保接口:理解为下单
	 */
	Result<Void> order(EmployeeForm employeeForm, Set<Integer> quote, Set<Integer> insure, VehiclePolicyTips tips);
	
	/**
	 * 获取报价信息
	 * @param employeeForm
	 * @param insurId
	 * @return
	 */
	Result<PolicySchema> quoteResult(EmployeeForm employeeForm, String license, int insurId);
	
	/**
	 * 获取投保结果(核保信息)
	 * 
	 * @param employeeForm
	 * @return
	 */
	Result<PolicyDetail> insureResult(EmployeeForm employeeForm, String license, int insurId);
}
