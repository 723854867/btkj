package org.btkj.vehicle.cache;

import org.btkj.vehicle.cache.domain.CfgCoefficientStructure;
import org.rapid.util.common.cache.JsonCache;

public class CfgCoefficientStructureCache extends JsonCache<Integer, CfgCoefficientStructure> {

	public CfgCoefficientStructureCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
