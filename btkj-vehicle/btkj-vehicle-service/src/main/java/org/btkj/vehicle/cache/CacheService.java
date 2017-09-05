package org.btkj.vehicle.cache;

import org.rapid.util.common.cache.JsonCache;
import org.springframework.stereotype.Component;

@Component("cacheService")
public class CacheService extends org.rapid.util.common.cache.CacheService<JsonCache<?, ?>> {
	
	public static final String CFG_VEHICLE	 				= "cfgVehicle";
	public static final String POUNDAGE_NODE 				= "poundageNode";
	public static final String COEFFICIENT_NODE 			= "coefficientNode";
	public static final String COEFFICIENT_RANGE 			= "coefficientRange";

	public CacheService() {
		addCache(new CfgVehicleCache(CFG_VEHICLE, 								"classpath:conf/json/cfg_vehicle.json"));
		addCache(new PoundageNodeCache(POUNDAGE_NODE, 							"classpath:conf/json/poundage_node.json"));
		addCache(new CoefficientNodeCache(COEFFICIENT_NODE, 					"classpath:conf/json/coefficient_node.json"));
		addCache(new CoefficientRangeCache(COEFFICIENT_RANGE, 					"classpath:conf/json/coefficient_range.json"));
	}
}
