package org.btkj.bihu.vehicle.api;

import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.bo.PolicyDetail;
import org.btkj.pojo.bo.PolicySchema;
import org.btkj.pojo.po.Renewal;
import org.btkj.pojo.vo.VehiclePolicyTips;
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
	Result<Renewal> renewal(EmployeeForm employeeForm, String license, int cityCode);
	
	/**
	 * 获取续保信息：通过车架号和发动机号
	 * 
	 * @param employeeForm
	 * @param vin
	 * @param engine
	 * @param name
	 * @return
	 */
	Result<Renewal> renewal(EmployeeForm employeeForm, String vin, String engine, int cityCode);
	
	/**
	 * 报价/投保接口:理解为下单
	 */
	Result<Void> order(EmployeeForm employeeForm, int quoteMod, int insureMod, VehiclePolicyTips tips, int cityCode);
	
	/**
	 * 获取报价信息
	 * @param employeeForm
	 * @param insurId
	 * @return
	 */
	Result<PolicySchema> quoteResult(EmployeeForm employeeForm, String license, int insurer);
	
	/**
	 * 获取投保结果(核保信息)
	 * 
	 * @param employeeForm
	 * @return
	 */
	Result<PolicyDetail> insureResult(EmployeeForm employeeForm, String license, int insurer);
}
