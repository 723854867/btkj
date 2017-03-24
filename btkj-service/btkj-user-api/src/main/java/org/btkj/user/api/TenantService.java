package org.btkj.user.api;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.ApplyInfo;

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
	
	/**
	 * 获取所有的 banner
	 * 
	 * @return
	 */
	Map<Integer, Banner> getBanners();
	
	/**
	 * 代理公司获取审核列表
	 * 
	 * @return
	 */
	List<ApplyInfo> applyList(int tid);
}
