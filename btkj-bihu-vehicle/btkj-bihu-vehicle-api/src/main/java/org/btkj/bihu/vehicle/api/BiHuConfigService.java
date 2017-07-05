package org.btkj.bihu.vehicle.api;

import java.util.List;

import org.btkj.bihu.vehicle.pojo.entity.BiHuCity;
import org.btkj.bihu.vehicle.pojo.entity.BiHuInsurer;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

public interface BiHuConfigService {

	/**
	 * 获取壁虎支持的所有城市
	 * 
	 * @return
	 */
	List<BiHuCity> cities();
	
	/**
	 * 新增壁虎支持的城市
	 * 
	 * @param cid
	 * @param region
	 * @param name
	 */
	void addBiHuCity(int cid, Region region, String name);
	
	/**
	 * 删除壁虎支持的城市
	 * 
	 */
	void deleteBiHuCity(int code);
	
	/**
	 * 获取壁虎支持的所有险企
	 * 
	 * @return
	 */
	List<BiHuInsurer> insurers();
	
	/**
	 * 新加壁虎险企
	 * 
	 * @param code
	 * @param name
	 */
	void addBiHuInsurer(int code, String name);
	
	/**
	 * 删除壁虎支持的险企
	 * 
	 * @param id
	 */
	void deleteBiHuInsurer(int id);
	
	/**
	 * 商户壁虎配置
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Result<Pager<TenantConfig>> tenantConfigs(int page, int pageSize);
	
	/**
	 * 新增商户壁虎配置
	 * 
	 * @param tid
	 * @param agent
	 * @param key
	 */
	void addTenantConfig(int tid, String agent, String key);
	
	/**
	 * 删除商户壁虎配置
	 * 
	 * @param tid
	 */
	void deleteTenantConfig(int tid);
}
