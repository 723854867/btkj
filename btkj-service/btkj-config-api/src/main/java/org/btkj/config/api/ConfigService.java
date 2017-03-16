package org.btkj.config.api;

import java.util.Map;

import org.btkj.pojo.entity.Insurance;
import org.btkj.pojo.entity.RegionCity;
import org.btkj.pojo.entity.RegionDistrict;
import org.btkj.pojo.entity.RegionProvince;

public interface ConfigService {

	/**
	 * 获取所有的保险公司配置信息
	 * 
	 * @return
	 */
	Map<Integer, Insurance> getInsurances();
	
	/**
	 * 获取所有的市级行政区划
	 * 
	 * @return
	 */
	Map<Integer, RegionCity> getRegionCities();
	
	/**
	 * 获取所有的县级行政区划
	 * 
	 * @return
	 */
	Map<Integer, RegionDistrict> getRegionDistricts();
	
	/**
	 * 获取所有的省级行政区划
	 * 
	 * @return
	 */
	Map<Integer, RegionProvince> getRegionProvinces();
}
