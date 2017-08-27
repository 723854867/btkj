package org.btkj.vehicle.cache;

import org.rapid.util.common.cache.JsonCache;
import org.springframework.stereotype.Component;

@Component("cacheService")
public class CacheService extends org.rapid.util.common.cache.CacheService<JsonCache<?, ?>> {
	
	public static final String CFG_COEFFICIENT 				= "cfgCoefficient";
	public static final String CFG_POUNDAGE_NODE			= "cfgPoundageCache";
	public static final String CFG_COEFFICIENT_RANGE 		= "cfgCoefficientRange";
	public static final String CFG_POUNDAGE_STRUCTURE 		= "cfgPoundageStructure";
	public static final String CFG_COEFFICIENT_STRUCTURE 	= "cfgCoefficientStructure";

	public CacheService() {
		addCache(new CfgCoefficientCache(CFG_COEFFICIENT, 						"classpath:conf/json/cfg_coefficient.json"));
		addCache(new CfgPoundageNodeCache(CFG_POUNDAGE_NODE, 					"classpath:conf/json/cfg_poundage_node.json"));
		addCache(new CfgCoefficientRangeCache(CFG_COEFFICIENT_RANGE, 			"classpath:conf/json/cfg_coefficient_range.json"));
		addCache(new CfgPoundageStructureCache(CFG_POUNDAGE_STRUCTURE, 			"classpath:conf/json/cfg_poundage_structure.json"));
		addCache(new CfgCoefficientStructureCache(CFG_COEFFICIENT_STRUCTURE, 	"classpath:conf/json/cfg_coefficient_structure.json"));
	}
}
