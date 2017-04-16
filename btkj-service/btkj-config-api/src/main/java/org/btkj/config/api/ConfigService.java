package org.btkj.config.api;

import org.btkj.pojo.entity.Region;

public interface ConfigService {
	
	/**
	 * 通过 region code 获取 Region
	 * 
	 * @param region
	 * @return
	 */
	Region getRegionById(int region);
}
