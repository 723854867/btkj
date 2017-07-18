package org.btkj.user.api;

import org.btkj.pojo.bo.indentity.App;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.vo.MainPageInfo;
import org.rapid.util.common.message.Result;

public interface AppService {
	
	/**
	 * 通过 appId 获取 app
	 * 
	 * @param appId
	 * @return
	 */
	App app(Client client, int appId);
	
	/**
	 * 通过 appId 获取 App
	 * 
	 * @param appId
	 * @return
	 */
	AppPO getAppById(int appId);
	
	/**
	 * 非游客模式的首页：分为 app 首页、pc 端首页、管理后台首页
	 * 
	 * @param client
	 * @param user
	 * @param em
	 * @return
	 */
	Result<MainPageInfo> mainPage(User user, Employee employee);

	/**
	 * 添加 app
	 * 
	 * @param region app 的业务地区
	 * @param name app 的名字
	 * @param maxTenantsCount app 能添加的租户的上限
	 * @param maxArticlesCount app 能添加的最大咨询数
	 * @return
	 */
	AppPO addApp(int region, String name, int maxTenantsCount, int maxArticlesCount);
	
	/**
	 * 获取该 app 的代理公司数
	 * 
	 * @param app
	 * @return
	 */
	int tenantNum(AppPO app);
}
