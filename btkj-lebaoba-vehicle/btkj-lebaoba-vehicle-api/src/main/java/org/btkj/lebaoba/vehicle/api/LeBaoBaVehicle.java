package org.btkj.lebaoba.vehicle.api;

import java.util.List;
import java.util.Set;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.Insurer;
import org.btkj.pojo.vo.VehicleInfo;
import org.btkj.pojo.vo.VehiclePolicyTips;
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
	List<VehicleInfo> vehicleInfos(String vin);
	
	/**
	 * 报价/投保接口
	 */
	Result<Void> order(Employee employee, Set<Insurer> quote, Set<Insurer> insure, VehiclePolicyTips tips);
}
