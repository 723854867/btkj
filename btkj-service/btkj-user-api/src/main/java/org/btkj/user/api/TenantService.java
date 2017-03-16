package org.btkj.user.api;

import java.util.Map;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;

public interface TenantService {

	/**
	 * 获取所有的 app 数据
	 * 
	 * @return
	 */
	Map<Integer, App> getApps();
	
	/**
	 * 获取所有的 tenant 数据
	 * 
	 * @return
	 */
	Map<Integer, Tenant> getTenants();
}
