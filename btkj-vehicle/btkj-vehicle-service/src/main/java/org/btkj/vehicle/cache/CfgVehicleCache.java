package org.btkj.vehicle.cache;

import org.btkj.vehicle.cache.domain.CfgVehicle;
import org.rapid.util.common.cache.JsonCache;

public class CfgVehicleCache extends JsonCache<String, CfgVehicle> {

	public CfgVehicleCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
