package org.btkj.vehicle.api;

import java.util.List;

import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.EmployeePO;
import org.btkj.pojo.entity.user.TenantPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.entity.vehicle.Renewal;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.DeliveryInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.VehicleOrderListInfo;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.pojo.param.vehicle.VehicleOrdersParam;
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
	Result<Renewal> renewal(UserPO user, TenantPO tenant, EmployeePO employee, String license, String name);
	
	/**
	 * 获取续保信息：通过车架号和发动机号(次新车)
	 * 
	 * @param employeeForm
	 * @param vin 车架号
	 * @param engine 发动机号
	 * @param name 车主姓名
	 * @return
	 */
	Result<Renewal> renewal(UserPO user, TenantPO tenant, EmployeePO employee, String vin, String engine, String name);
	
	/**
	 * 下单：包括报价、投保、报价并投保
	 */
	Result<Void> order(AppPO app, TenantPO tenant, UserPO user, EmployeePO employee, VehicleOrderParam param);
	
	/**
	 * 查看订单详情
	 * 
	 * @param employeeForm
	 * @param id
	 * @return
	 */
	Result<VehicleOrder> orderInfo(TenantPO tenant, EmployeePO employee, String id);
	
	/**
	 * 获取商户的险企列表
	 * 
	 * @return
	 */
	List<Integer> insurers(int tid);
	
	/**
	 * 保单分页
	 * 
	 * @return
	 */
	Pager<VehicleOrderListInfo> orders(TenantPO tenant, EmployeePO employee, VehicleOrdersParam param);
	
	/**
	 * 根据车架号获取车辆信息
	 * @param vin
	 * @return
	 */
	List<VehicleInfo> vehicleInfos(TenantPO tenant, String vin);
	
	/**
	 * 编辑配送信息
	 * 
	 * @param orderId
	 * @param deliveryInfo
	 * @return
	 */
	Result<Void> deliveryEdit(String orderId, DeliveryInfo deliveryInfo);
	
	/**
	 * 获取指定状态的保单数量
	 * 
	 * @param employeeId
	 * @param begin
	 * @param end
	 * @param stateMod
	 * @return
	 */
	long orderNum(int employeeId, int begin, int end, int stateMod);
}
