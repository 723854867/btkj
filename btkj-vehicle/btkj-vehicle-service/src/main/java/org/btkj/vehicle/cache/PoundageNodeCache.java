package org.btkj.vehicle.cache;

import org.btkj.pojo.entity.vehicle.PoundageNode;
import org.rapid.util.common.cache.JsonCache;

public class PoundageNodeCache extends JsonCache<Integer, PoundageNode> {

	public PoundageNodeCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
