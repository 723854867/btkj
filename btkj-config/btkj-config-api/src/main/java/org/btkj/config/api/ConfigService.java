package org.btkj.config.api;

import java.util.List;

import org.btkj.pojo.entity.Insurer;
import org.btkj.pojo.entity.Region;

public interface ConfigService {
	
	/**
	 * 通过 region code 获取 Region
	 * 
	 * @param region
	 * @return
	 */
	Region getRegionById(int region);
	
	/**
	 * 根据保险公司ID获取保险公司
	 * 
	 * @param insurerId
	 * @return
	 */
	Insurer getInsurerById(int insurerId);
	
	/**
	 * 获取险企列表
	 * 
	 * @param list
	 * @return
	 */
	List<Insurer> insurers(List<Integer> list);
}
