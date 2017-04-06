package org.btkj.config.api;

import java.util.Map;

import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Region;

public interface ConfigService {
	
	/**
	 * 获取所有的市级行政区划
	 * 
	 * @return
	 */
	Map<Integer, Region> getRegions();

	/**
	 * 获取所有的保险公司配置信息
	 * 
	 * @return
	 */
	Map<Integer, Insurer> getInsurances();
}
