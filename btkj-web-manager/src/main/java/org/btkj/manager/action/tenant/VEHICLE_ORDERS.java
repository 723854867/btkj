package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.info.VehicleOrderListInfo;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.model.Pager;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.param.VehicleOrdersParam;
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
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, VehicleOrdersParam param) {
		param.setAppId(app.getId());
		param.setTid(tenant.getTid());
		Pager<VehicleOrder> pager = vehicleManageService.orders(param);
		if (CollectionUtil.isEmpty(pager.getList()))
			return Result.result(pager);
		Set<Integer> uids = new HashSet<Integer>();
		for (VehicleOrder info : pager.getList())
			uids.add(info.getUid());
		
		List<VehicleOrderListInfo> list = new ArrayList<VehicleOrderListInfo>();
		Map<Integer, UserPO> users = userService.users(uids);
		for (VehicleOrder order : pager.getList()) 
			list.add(new VehicleOrderListInfo(users.get(order.getUid()), order));
		return Result.result(new Pager<VehicleOrderListInfo>(pager.getTotal(), list));
	}
}
