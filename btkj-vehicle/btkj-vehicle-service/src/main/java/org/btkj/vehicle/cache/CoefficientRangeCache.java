package org.btkj.vehicle.cache;

import org.btkj.vehicle.pojo.entity.CoefficientRange;
import org.rapid.util.common.cache.JsonCache;

public class CoefficientRangeCache extends JsonCache<Integer, CoefficientRange> {

	public CoefficientRangeCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
