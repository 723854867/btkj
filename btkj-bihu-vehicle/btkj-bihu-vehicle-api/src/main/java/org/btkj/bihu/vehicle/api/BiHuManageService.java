package org.btkj.bihu.vehicle.api;

import java.util.List;

import org.btkj.bihu.vehicle.pojo.entity.BiHuArea;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.pojo.entity.Region;
import org.rapid.util.common.message.Result;

public interface BiHuManageService {
	
	TenantConfig tenantConfig(int tid);
	
	Result<Void> tenantConfigAdd(int tid, String agent, String key);
	
	Result<Void> tenantConfigUpdate(int tid, String agent, String key);
	
	void tenantConfigDelete(int tid);
	
	/**
	 * 获取壁虎支持的所有城市
	 * 
	 * @return
	 */
	List<BiHuArea> cities();
	
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
}
