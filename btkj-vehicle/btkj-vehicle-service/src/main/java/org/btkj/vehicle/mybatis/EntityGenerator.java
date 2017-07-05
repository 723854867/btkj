package org.btkj.vehicle.mybatis;

import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.City;
import org.btkj.vehicle.pojo.entity.Route;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.DateUtils;

public class EntityGenerator {

	public static final City newCity(int region, String name, int renewalPeriod) {
		City city = new City();
		city.setCode(region);
		city.setName(name);
		city.setRenewalPeriod(renewalPeriod);
		
		int time = DateUtils.currentTime();
		city.setCreated(time);
		city.setUpdated(time);
		return city;
	}
	
	public static final Route newRoute(int tid, int insurerId, Lane lane) { 
		Route route = new Route();
		route.setKey(tid + Consts.SYMBOL_UNDERLINE + insurerId);
		route.setTid(tid);
		route.setInsurerId(insurerId);
		route.setLane(lane.mark());
		
		int time = DateUtils.currentTime();
		route.setCreated(time);
		route.setUpdated(time);
		return route;
	}
}
