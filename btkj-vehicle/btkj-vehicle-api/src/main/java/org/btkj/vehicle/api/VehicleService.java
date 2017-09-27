package org.btkj.vehicle.api;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.Renewal;
import org.btkj.pojo.entity.vehicle.TenantInsurer;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.info.VehicleInfo;
import org.btkj.pojo.model.DeliveryInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.model.VehicleOrderListInfo;
import org.btkj.pojo.param.VehicleOrderParam;
import org.btkj.pojo.param.vehicle.TenantInsurerEditParam;
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
	Result<Renewal> renewal(User user, Tenant tenant, Employee employee, String license, String name);
	
	/**
	 * 获取续保信息：通过车架号和发动机号(次新车)
	 * 
	 * @param employeeForm
	 * @param vin 车架号
	 * @param engine 发动机号
	 * @param name 车主姓名
	 * @return
	 */
	Result<Renewal> renewal(User user, Tenant tenant, Employee employee, String vin, String engine, String name);
	
	/**
	 * 下单：包括报价、投保、报价并投保
	 */
	Result<Void> order(App app, Tenant tenant, User user, Employee employee, VehicleOrderParam param);
	
	/**
	 * 查看订单详情
	 * 
	 * @param employeeForm
	 * @param id
	 * @return
	 */
	Result<VehicleOrder> orderInfo(Tenant tenant, Employee employee, String id);
	
	/**
	 * 保单分页
	 * 
	 * @return
	 */
	Pager<VehicleOrderListInfo> orders(Tenant tenant, Employee employee, VehicleOrdersParam param);
	
	/**
	 * 根据车架号获取车辆信息
	 * @param vin
	 * @return
	 */
	List<VehicleInfo> vehicleInfos(Tenant tenant, String vin);
	
	/**
	 * 编辑配送信息
	 * 
	 * @param orderId
	 * @param deliveryInfo
	 * @return
	 */
	Result<Void> deliveryEdit(String orderId, DeliveryInfo deliveryInfo);
	
	Result<Void> insurerEdit(TenantInsurerEditParam param);
	
	Map<String, TenantInsurer> insurers(int tid, Boolean minor);
}
