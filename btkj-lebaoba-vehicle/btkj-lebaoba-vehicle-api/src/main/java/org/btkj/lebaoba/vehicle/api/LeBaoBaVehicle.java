package org.btkj.lebaoba.vehicle.api;

import java.util.List;

import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.param.VehicleOrderParam;
import org.rapid.util.common.message.Result;

/**
 * 乐保吧车险
 * 
 * @author ahab
 */
public interface LeBaoBaVehicle {
	
	/**
	 * 根据车架号获取车型信息列表
	 * 
	 * @param vin
	 * @return
	 */
	List<VehicleInfo> vehicleInfos(String username, String password, String vin);
	
	/**
	 * 报价/投保接口
	 */
	Result<PolicySchema> order(String username, String password, String insurer, boolean insure, VehicleOrderParam param);
}
