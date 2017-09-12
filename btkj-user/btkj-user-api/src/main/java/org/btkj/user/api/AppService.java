package org.btkj.user.api;

import java.util.Collection;
import java.util.Map;

import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.MainPageInfo;
import org.btkj.pojo.model.identity.App;
import org.btkj.pojo.model.identity.Employee;
import org.btkj.pojo.model.identity.User;
import org.rapid.util.common.message.Result;

public interface AppService {
	
	/**
	 * 通过 appId 获取 App
	 * 
	 * @param appId
	 * @return
	 */
	AppPO app(int appId);
	
	Map<Integer, AppPO> apps(Collection<Integer> apps);
	
	/**
	 * 通过 appId 获取 app
	 * 
	 * @param appId
	 * @return
	 */
	App app(Client client, int appId);
	
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
	 * 获取该 app 的代理公司数
	 * 
	 * @param app
	 * @return
	 */
	int tenantNum(AppPO app);
	
	/**
	 * 禁用 app
	 * @param app
	 */
	Result<Void> seal(int appId);
	
	/**
	 * 解禁 app
	 * 
	 * @param appId
	 * @return
	 */
	Result<Void> unseal(int appId);
}
