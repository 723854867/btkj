package org.btkj.vehicle.cache;

import org.btkj.vehicle.cache.domain.CfgPoundageNode;
import org.rapid.util.common.cache.JsonCache;

/**
 * 手续费节点配置
 * 
 * @author ahab
 */
public class CfgPoundageNodeCache extends JsonCache<Integer, CfgPoundageNode> {
	
	public CfgPoundageNodeCache(String name, String jsonFileLocation) {
		super(name, jsonFileLocation);
	}
}
