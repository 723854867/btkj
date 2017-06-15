package org.btkj.vehicle.api;

import java.util.List;

import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.model.VehicleOrderSearcher;
import org.rapid.util.common.message.Result;

/**
 * 续保服务类：专门用来获取车辆的续保信息
 * 
 * @author ahab
 */
public interface VehicleService {

	/**
	 * 获取续保信息：通过车牌号(已上牌)
	 * 
	 * @param employee
	 * @param license 车牌号
	 * @param name 车主姓名
	 */
	Result<Renewal> renewal(EmployeeForm employeeForm, String license, String name);
	
	/**
	 * 获取续保信息：通过车架号和发动机号(次新车)
	 * 
	 * @param employeeForm
	 * @param vin 车架号
	 * @param engine 发动机号
	 * @param name 车主姓名
	 * @return
	 */
	Result<Renewal> renewal(EmployeeForm employeeForm, String vin, String engine, String name);
	
	/**
	 * 下单：包括报价、投保、报价并投保
	 * 
	 * @param employeeForm
	 * @param tips
	 * @return
	 */
	Result<Void> order(List<Insurer> quote, List<Insurer> insure, EmployeeForm employeeForm, VehiclePolicyTips tips);
	
	/**
	 * 查看订单详情
	 * 
	 * @param employeeForm
	 * @param id
	 * @return
	 */
	Result<VehicleOrder> orderInfo(EmployeeForm employeeForm, String id);
	
	/**
	 * 获取商户的险企列表
	 * 
	 * @return
	 */
	List<Integer> insurers(Tenant tenant);
	
	/**
	 * 保单分页
	 * 
	 * @return
	 */
	Pager<VehicleOrder> orders(EmployeeForm ef, VehicleOrderSearcher searcher);
}
