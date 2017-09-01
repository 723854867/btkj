package org.btkj.vehicle.cache;

import org.btkj.vehicle.pojo.entity.CoefficientNode;
import org.rapid.util.common.cache.JsonCache;

public class CoefficientNodeCache extends JsonCache<Integer, CoefficientNode> {

	public CoefficientNodeCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
