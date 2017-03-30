package org.btkj.user.api;

import java.util.Map;

import org.btkj.pojo.Region;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

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
	Result<Pager<ApplyInfo>> applyList(int tid, int page, int pageSize);
	
	/**
	 * 处理请求
	 * 
	 * @param tid
	 * @param applyKey
	 * @param agree
	 */
	Result<?> applyProcess(int tid, String applyKey, boolean agree);
	
	// ****************
	/**
	 * 添加代理公司
	 * 
	 * @param region 代理公司地区
	 * @param appName  代理公司 app 名字，如果为 null 则默认添加保途租户
	 * @param tenantName 代理公司租户名字
	 * @param name root 账号名字
	 * @param mobile root 账号手机名字
	 * @param identity root 账号身份证
	 * @return
	 */
	Result<Void> tenantAdd(Region region, String appName, String tenantName, String name, String mobile, String identity);
}
