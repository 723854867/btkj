package org.btkj.vehicle.cache;

import org.btkj.vehicle.cache.domain.CfgCoefficientRange;
import org.rapid.util.common.cache.JsonCache;

public class CfgCoefficientRangeCache extends JsonCache<Integer, CfgCoefficientRange> {

	public CfgCoefficientRangeCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
