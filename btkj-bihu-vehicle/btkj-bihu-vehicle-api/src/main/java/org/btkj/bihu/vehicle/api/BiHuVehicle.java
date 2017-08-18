package org.btkj.bihu.vehicle.api;

import java.util.List;

import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.model.VehicleAuditModel;
import org.btkj.pojo.param.VehicleOrderParam;
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
	Result<Renewal> renewal(TenantPO tenant, int uid, String license, int cityCode);
	
	/**
	 * 获取续保信息：通过车架号和发动机号
	 * 
	 * @param employeeForm
	 * @param vin
	 * @param engine
	 * @param name
	 * @return
	 */
	Result<Renewal> renewal(TenantPO tenant, int uid, String vin, String engine, int cityCode);
	
	/**
	 * 获取车辆信息
	 * 
	 * @return
	 */
	Result<List<VehicleInfo>> vehicleInfos(int uid, TenantPO tenant, String license, String modelName, int cityCode);
	
	/**
	 * 报价/投保接口:理解为下单
	 */
	Result<Void> order(String agent, String key, int uid, int quoteMod, int insureMod, int cityCode, VehicleOrderParam param);
	
	/**
	 * 获取报价信息
	 * @param employeeForm
	 * @param insurId
	 * @return
	 */
	Result<PolicySchema> quoteResult(TenantPO tenant, int uid, String license, int insurer);
	
	/**
	 * 获取投保结果(核保信息)
	 * 
	 * @param employeeForm
	 * @return
	 */
	Result<VehicleAuditModel> insureResult(TenantPO tenant, int uid, String license, int insurer);
}
