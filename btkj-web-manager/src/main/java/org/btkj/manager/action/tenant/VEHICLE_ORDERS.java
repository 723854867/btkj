package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.TenantAction;
import org.btkj.manager.pojo.info.VehicleOrderListInfo;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.UserPO;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.user.api.UserService;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;

/**
 * 车险订单
 * 
 * @author ahab
 */
public class VEHICLE_ORDERS extends TenantAction {
	
	@Resource
	private UserService userService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(Request request, Employee employee) {
		VehicleOrderSearcher searcher = request.getParam(Params.VEHICLE_ORDER_SEARCHER);
		searcher.setTid(employee.getTid());
		Pager<VehicleOrder> pager = vehicleManageService.orders(searcher);
		if (CollectionUtil.isEmpty(pager.getList()))
			return Result.result(pager);
		List<VehicleOrder> orders = pager.getList();
		Set<Integer> uids = new HashSet<Integer>();
		for (VehicleOrder info : orders)
			uids.add(info.getUid());
		List<VehicleOrderListInfo> list = new ArrayList<VehicleOrderListInfo>();
		Map<Integer, UserPO> users = userService.users(uids);
		for (VehicleOrder order : orders) {
			UserPO user = users.get(order.getUid());
			list.add(new VehicleOrderListInfo(user, order));
		}
		return Result.result(new Pager<VehicleOrderListInfo>(pager.getTotal(), list));
	}
}
