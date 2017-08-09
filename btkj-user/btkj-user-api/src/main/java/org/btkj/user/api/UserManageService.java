package org.btkj.user.api;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.user.pojo.info.AppInfo;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.model.BonusScale;
import org.btkj.user.pojo.param.AppEditParam;
import org.btkj.user.pojo.param.EmployeeEditParam;
import org.btkj.user.pojo.param.EmployeesParam;
import org.btkj.user.pojo.param.TenantSetParam;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.user.pojo.submit.UserSearcher;
import org.rapid.util.common.message.Result;

public interface UserManageService {
	
	/**
	 * 用户分页列表
	 * 
	 * @param searcher
	 * @return
	 */
	Result<Pager<UserPagingInfo>> userPaging(UserSearcher searcher);

	/**
	 * 代理公司分页列表
	 * 
	 * @param searcher
	 * @return
	 */
	Result<Pager<TenantPagingInfo>> tenantPaging(TenantSearcher searcher);
	
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
	 * 编辑轮播图
	 * 
	 * @param appId
	 * @param tid
	 * @param idx
	 * @param icon
	 * @param link
	 * @return
	 */
	Result<Void> bannerAdd(int appId, int tid, int idx, String icon, String link);
	
	/**
	 * 新增轮播图
	 * 
	 * @param appId
	 * @param tid
	 * @param idx
	 * @param icon
	 * @param link
	 * @return
	 */
	Result<Void> bannerEdit(int id, String icon, String link);
	
	/**
	 * 删除轮播图
	 * 
	 * @param id
	 * @return
	 */
	Result<Void> bannerDelete(int id);
	
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
	 * @param user
	 * @param name
	 * @param license
	 * @param licenseImage
	 * @param expire
	 * @return
	 */
	Result<Void> tenantSet(User user, int tid, String contacts, String contactsMobile, String tname, String license, String licenseImage, int expire);
	
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
