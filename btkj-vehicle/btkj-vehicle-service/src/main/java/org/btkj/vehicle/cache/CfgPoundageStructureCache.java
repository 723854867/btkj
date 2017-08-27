package org.btkj.vehicle.cache;

import org.btkj.vehicle.cache.domain.CfgPoundageStructure;
import org.rapid.util.common.cache.JsonCache;

public class CfgPoundageStructureCache extends JsonCache<Integer, CfgPoundageStructure> {

	public CfgPoundageStructureCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
