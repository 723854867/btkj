package org.btkj.master.action.vehicle.baotu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.config.api.ConfigService;
import org.btkj.master.LoggedAction;
import org.btkj.master.pojo.RouteInfo;
import org.btkj.pojo.po.Administrator;
import org.btkj.pojo.po.Insurer;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.rapid.util.common.message.Result;

public class ROUTES extends LoggedAction {
	
	@Resource
	private ConfigService configService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<RouteInfo>> execute(Request request, Administrator operator) {
		List<Route> routes = vehicleManageService.routes(request.getParam(Params.TID));
		Set<Integer> set = new HashSet<Integer>();
		for (Route route : routes)
			set.add(route.getInsurerId());
		Map<Integer, Insurer> insurers = configService.insurers(new ArrayList<Integer>(set));
		List<RouteInfo> l = new ArrayList<RouteInfo>();
		for (Route route : routes) {
			Insurer insurer = insurers.get(route.getInsurerId());
			if (null == insurer)
				continue;
			l.add(new RouteInfo(route, insurer));
			break;
		}
		return Result.result(l);
	}
}
