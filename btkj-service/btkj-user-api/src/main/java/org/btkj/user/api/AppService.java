package org.btkj.user.api;

import org.btkj.pojo.info.AppCreateInfo;
import org.rapid.util.common.message.Result;

public interface AppService {

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
