package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.info.VehicleOrderListInfo;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.VehicleOrder;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.vehicle.VehicleOrdersParam;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 车险订单
 * 
 * @author ahab
 */
public class VEHICLE_ORDERS extends EmployeeAction<VehicleOrdersParam> {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, VehicleOrdersParam param) {
		param.setAppId(app.getId());
		param.setTid(tenant.getTid());
		Pager<VehicleOrder> pager = vehicleManageService.orders(param);
		if (CollectionUtil.isEmpty(pager.getList()))
			return Result.result(pager);
		Set<Integer> uids = new HashSet<Integer>();
		for (VehicleOrder info : pager.getList())
			uids.add(info.getUid());
		
		List<VehicleOrderListInfo> list = new ArrayList<VehicleOrderListInfo>();
		Map<Integer, User> users = userService.users(uids);
		for (VehicleOrder order : pager.getList()) 
			list.add(new VehicleOrderListInfo(users.get(order.getUid()), order));
		return Result.result(new Pager<VehicleOrderListInfo>(pager.getTotal(), list));
	}
}
