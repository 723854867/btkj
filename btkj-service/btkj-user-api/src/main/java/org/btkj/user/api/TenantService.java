package org.btkj.user.api;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
import org.btkj.pojo.model.EmployeeModel;
import org.btkj.pojo.model.Pager;
import org.rapid.util.common.message.Result;

public interface TenantService {
	
	/**
	 * 通过 tid 获取 tenant
	 * 
	 * @param tid
	 * @return
	 */
	Tenant getTenantById(int tid);
	
	/**
	 * 通过手机号和 tid 获取 EmployeeModel
	 * 
	 * @param mobile
	 * @param tid
	 * @return
	 */
	Result<EmployeeModel> employee(String mobile, int tid);
	
	/**
	 * 申请加入代理公司
	 * 
	 * @param token
	 * @param employeeId
	 * @return
	 */
	Result<?> apply(User user, int employeeId);

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
	 * @param agree
	 */
	Result<Void> applyProcess(int tid, int uid, boolean agree);
	
	/**
	 * 添加代理公司
	 * 
	 * @param app
	 * @param region 代理公司地区
	 * @param tenantName 代理公司租户名字
	 * @param pwd 密码
	 * @param uid 管理员用户ID
	 * @return
	 */
	Result<Void> tenantAdd(App app, Region region, String tenantName, String pwd, int uid);

	/**
	 * 添加代理公司
	 * 
	 * @param app 如果是为多租户app添加代理公司则需要该参数
	 * @param region 代理公司地区
	 * @param tenantName 代理公司租户名字
	 * @param name root 账号名字
	 * @param mobile root 账号手机名字
	 * @param identity root 账号身份证
	 * @param pwd 默认所有用户的 pc 和管理后台登录密码
	 * @return
	 */
	Result<Void> tenantAdd(App app, Region region, String tenantName, String name, String mobile, String identity, String pwd);
	
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
}
