package org.btkj.vehicle.cache;

import org.btkj.vehicle.cache.domain.CfgCoefficient;
import org.rapid.util.common.cache.JsonCache;

public class CfgCoefficientCache extends JsonCache<Integer, CfgCoefficient> {

	public CfgCoefficientCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
