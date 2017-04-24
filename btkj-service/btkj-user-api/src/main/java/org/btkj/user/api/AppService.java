package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.AppCreateInfo;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
import org.rapid.util.common.message.Result;

public interface AppService {
	
	/**
	 * 通过 appId 获取 App
	 * 
	 * @param appId
	 * @return
	 */
	App getAppById(int appId);
	
	/**
	 * 首页:默认 app 首页
	 * 
	 * @return
	 */
	Result<IMainPageInfo> mainPage(int appId); 
	
	/**
	 * 非游客模式的首页：分为 app 首页、pc 端首页、管理后台首页
	 * 
	 * @param client
	 * @param token
	 * @param tid
	 * @return
	 */
	Result<IMainPageInfo> mainPage(Client client, String token, int tid);

	/**
	 * 添加 app
	 * 
	 * @param region app 的业务地区
	 * @param appName app 的名字
	 * @param maxTenantsCount app 能添加的租户的上限
	 * @param tenantRegion 代理公司的业务地区
	 * @param tenantName 代理公司的名字
	 * @param pwd 代理公司后台的初始密码
	 * @param mobile root 用户的手机号
	 * @param name root 用户的名字
	 * @param identity root 用户的身份证
	 * @return
	 */
	Result<AppCreateInfo> addApp(int region, String appName, int maxTenantsCount, int tenantRegion, String tenantName, String pwd, String mobile, String name, String identity);
}
