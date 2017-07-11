package org.btkj.vehicle.api;

import java.util.List;

import org.btkj.pojo.entity.Tenant;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.City;
import org.btkj.vehicle.pojo.entity.Route;

public interface VehicleConfigService {

	/**
	 * 获取所有的城市
	 * 
	 * @return
	 */
	List<City> cities();
	
	/**
	 * 新增城市
	 * 
	 * @param code
	 * @param name
	 * @param renewalPeriod
	 */
	void addCity(int region, String name, int renewalPeriod);
	
	/**
	 * 删除城市
	 * 
	 * @param region
	 */
	void deleteCity(int region);
	
	/**
	 * 获取商户车险路由
	 * 
	 * @param tenant
	 * @return
	 */
	List<Route> routes(Tenant tenant);
	
	/**
	 * 新增车险路由
	 * 
	 * @param tid
	 * @param insurerId
	 * @param lane
	 */
	void addRoute(int tid, int insurerId, Lane lane);
	
	/**
	 * 删除路由
	 * 
	 * @param key
	 */
	void deleteRoute(String key);
	
	/**
	 * 获取指定商户的管理佣金设置
	 * 
	 * @param tid
	 * @return
	 */
	List<BonusManageConfig> bonusManageConfigs(int tid);
}
