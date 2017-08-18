package org.btkj.user.api;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.user.pojo.info.AppInfo;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.model.BonusScale;
import org.btkj.user.pojo.param.AppEditParam;
import org.btkj.user.pojo.param.BannerEditParam;
import org.btkj.user.pojo.param.EmployeeEditParam;
import org.btkj.user.pojo.param.EmployeesParam;
import org.btkj.user.pojo.param.TenantSetPTParam;
import org.btkj.user.pojo.param.TenantSetParam;
import org.btkj.user.pojo.param.TenantsParam;
import org.btkj.user.pojo.param.UsersParam;
import org.rapid.util.common.message.Result;

public interface UserManageService {
	
	/**
	 * 用户分页列表
	 * 
	 * @param searcher
	 * @return
	 */
	Result<Pager<UserPagingInfo>> users(UsersParam param);

	/**
	 * 代理公司分页列表
	 * 
	 * @param searcher
	 * @return
	 */
	Result<Pager<TenantPagingInfo>> tenants(TenantsParam param);
	
	/**
	 * 修改商户
	 * 
	 * @param tenant
	 */
	void tenantUpdate(TenantPO tenant);
	
	/**
	 * 雇员分页列表
	 * 
	 * @param searcher
	 * @return
	 */
	Result<Pager<EmployeePagingInfo>> employees(EmployeesParam param);
	
	/**
	 * 申请分页列表
	 * 
	 * @param tid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Result<Pager<ApplyPagingInfo>> applies(int tid, int page, int pageSize);
	
	/**
	 * 申请审核
	 * 
	 * @param tid
	 * @param uid
	 * @param reject
	 * @return
	 */
	Result<EmployeeTip> applyAudit(int tid, int uid, boolean reject);
	
	/**
	 * 员工编辑
	 * 
	 * @param employeeId
	 * @param targetId
	 * @param mod
	 * @return
	 */
	Result<Void> employeeEdit(int tid, int employeeId, EmployeeEditParam param);
	
	/**
	 * 轮播图编辑
	 * 
	 * @param param
	 * @return
	 */
	Result<Void> bannerEdit(BannerEditParam param);
	
	/**
	 * 商家设置(商家自己)
	 * 
	 * @param tenant
	 * @param param
	 * @return
	 */
	Result<Void> tenantSet(TenantPO tenant, TenantSetParam param);
	
	/**
	 * 商家设置(平台)
	 * 
	 * @param param
	 * @return
	 */
	Result<Void> tenantSet(UserPO user, TenantSetPTParam param);
	
	/**
	 * 所有平台
	 * 
	 * @return
	 */
	List<AppInfo> apps();
	
	/**
	 * 编辑平台
	 * 
	 * @param param
	 * @return
	 */
	Result<?> appEdit(AppEditParam param);
	
	/**
	 * 根据个人的总业绩获取每个人的团队总业绩
	 * 
	 * @param personalExploits
	 * @return
	 */
	void calculateTeamExploits(int time, TenantPO tenant, Map<Integer, BonusScale> personalExploits);
	
	/**
	 * 获取指定规模奖励审核数据
	 * 
	 * @return
	 */
	BonusScale bonusScale(String key);
	
	/**
	 * 规模奖励审核列表
	 * 
	 * @param tid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Pager<BonusScale> bonusScales(int tid, EmployeeParam param);
	
	/**
	 * 规模奖励审核
	 * 
	 * @param tid
	 * @param key
	 * @param agree
	 * @return
	 */
	Result<BonusScale> bonusScaleAudit(int tid, String key, boolean agree);
}
