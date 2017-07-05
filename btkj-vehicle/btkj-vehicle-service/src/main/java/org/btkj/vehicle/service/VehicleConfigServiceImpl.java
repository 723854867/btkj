package org.btkj.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Tenant;
import org.btkj.vehicle.api.VehicleConfigService;
import org.btkj.vehicle.mybatis.EntityGenerator;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.City;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.vehicle.redis.CityMapper;
import org.btkj.vehicle.redis.RouteMapper;
import org.springframework.stereotype.Service;

@Service("vehicleConfigService")
public class VehicleConfigServiceImpl implements VehicleConfigService {
	
	@Resource
	private CityMapper cityMapper;
	@Resource
	private RouteMapper routeMapper;

	@Override
	public List<City> cities() {
		return cityMapper.getAll();
	}

	@Override
	public void addCity(int region, String name, int renewalPeriod) {
		City city = EntityGenerator.newCity(region, name, renewalPeriod);
		cityMapper.insert(city);
	}

	@Override
	public void deleteCity(int region) {
		cityMapper.delete(region);
	}
	
	@Override
	public List<Route> routes(Tenant tenant) {
		return routeMapper.routes(tenant);
	}
	
	@Override
	public void addRoute(int tid, int insurerId, Lane lane) {
		Route route = EntityGenerator.newRoute(tid, insurerId, lane);
		routeMapper.insert(route);
	}
	
	public void deleteRoute(String key) {
		routeMapper.delete(key);
	}
}
