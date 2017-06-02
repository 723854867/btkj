package org.btkj.user.api;

import java.util.List;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.NonAutoBind;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.enums.TenantState;
import org.btkj.pojo.info.ApplyInfo;
import org.btkj.pojo.info.TenantListInfo;
import org.btkj.pojo.info.TenantListPc;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.TenantSearcher;
import org.rapid.util.common.message.Result;

public interface TenantService {
	
	/**
	 * 修改保存Tenant基本信息
	 * @return
	 */
	Result<Void> tenantEdit(Tenant tenant);
	
	/**
	 * 禁用Tenant
	 * 
	 * @param state
	 * @return
	 */
	Result<Void> tenantState(int tenantId, TenantState state);
	
	/**
	 * 获取平台Tenant用户列表
	 */
	Result<Pager<TenantListPc>> tenantList(TenantSearcher searcher);
	
	/**
	 * 通过 tid 获取 tenant
	 * 
	 * @param tid
	 * @return
	 */
	Tenant getTenantById(int tid);
	
	/**
	 * 申请加入代理公司
	 * 
	 * @param user
	 * @param chief
	 * @return
	 */
	Result<?> apply(User user, EmployeeForm chief);

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
	 * @param app 如果是为多租户app添加代理公司则需要该参数
	 * @param region 代理公司地区
	 * @param tname 代理公司租户名字
	 * @param user 顶级用户
	 * @return
	 */
	Result<?> tenantAdd(App app, Region region, String tname, User user);
	
	/**
	 * 代理公司列表
	 * 
	 * @param client
	 * @param app
	 * @param user
	 * @return
	 */
	TenantListInfo tenantListInfo(Client client, App app, User user);
	
	/**
	 * 获取代理公司非车险列表
	 * 
	 * @return
	 */
	List<NonAutoBind> getNonAutoBinds(int tid);
}
