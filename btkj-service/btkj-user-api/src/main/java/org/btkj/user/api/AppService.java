package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.AppState;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.AppListInfo;
import org.btkj.pojo.info.MainPageInfo;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.AppSearcher;
import org.rapid.util.common.message.Result;

public interface AppService {
	
	/**
	 * 禁用App
	 * 
	 * @param state
	 * @return
	 */
	Result<Void> appState(int appId, AppState state);
	
	/**
	 * 获取全平台App用户
	 */
	Result<Pager<AppListInfo>> appList(AppSearcher searcher);
	
	/**
	 * 通过 appId 获取 App
	 * 
	 * @param appId
	 * @return
	 */
	App getAppById(int appId);
	
	/**
	 * 非游客模式的首页：分为 app 首页、pc 端首页、管理后台首页
	 * 
	 * @param client
	 * @param user
	 * @param em
	 * @return
	 */
	Result<MainPageInfo> mainPage(Client client, User user, EmployeeForm em);

	/**
	 * 添加 app
	 * 
	 * @param region app 的业务地区
	 * @param name app 的名字
	 * @param maxTenantsCount app 能添加的租户的上限
	 * @param tenantAddAutonomy 是否允许自主添加代理商，如果为 false，则需要保途审核
	 * @return
	 */
	App addApp(int region, String name, int maxTenantsCount, boolean tenantAddAutonomy);
	
	/**
	 * 获取该 app 的代理公司数
	 * 
	 * @param app
	 * @return
	 */
	int tenantNum(App app);
}
