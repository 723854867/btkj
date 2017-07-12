package org.btkj.vehicle.mybatis;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.vehicle.pojo.BonusScaleConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.City;
import org.btkj.vehicle.pojo.entity.Route;
import org.rapid.util.common.Consts;
import org.rapid.util.lang.DateUtils;
import org.rapid.util.math.compare.ComparisonSymbol;

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
	
	public static final VehicleCoefficient newVehicleCoefficient(int tid, CoefficientType type, ComparisonSymbol symbol, String value, String name) {
		VehicleCoefficient coefficient = new VehicleCoefficient();
		coefficient.setTid(tid);
		coefficient.setName(name);
		coefficient.setType(type.mark());
		coefficient.setComparableValue(value);
		coefficient.setComparison(symbol.mark());
		
		int time = DateUtils.currentTime();
		coefficient.setCreated(time);
		coefficient.setUpdated(time);
		return coefficient;
	}
	
	public static final BonusManageConfig newBonusManageConfig(int tid, BonusScaleConfigType type, int depth, int rate) {
		BonusManageConfig config = new BonusManageConfig();
		config.setTid(tid);
		config.setRate(rate);
		config.setType(type.mark());
		config.setDepth(depth);
		config.setKey(tid + Consts.SYMBOL_UNDERLINE + type.mark() + Consts.SYMBOL_UNDERLINE + depth);
		
		int time = DateUtils.currentTime();
		config.setCreated(time);
		config.setUpdated(time);
		return config;
	}
}
