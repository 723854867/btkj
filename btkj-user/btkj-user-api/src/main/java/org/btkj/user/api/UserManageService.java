package org.btkj.user.api;

import java.util.List;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.bo.indentity.User;
import org.btkj.user.pojo.info.AppInfo;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.user.pojo.submit.TenantSettingsSubmit;
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
	Result<Pager<EmployeePagingInfo>> employeePaging(EmployeeSearcher searcher);
	
	/**
	 * 申请分页列表
	 * 
	 * @param tid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Result<Pager<ApplyPagingInfo>> applyPaging(int tid, int page, int pageSize);
	
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
	 * @param submit
	 * @return
	 */
	Result<Void> tenantSet(Employee employee, TenantSettingsSubmit submit);
	
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
	 * 新增平台
	 * 
	 * @param region
	 * @param name
	 * @param maxTenantsCount
	 * @param maxArticlesCount
	 * @return
	 */
	Result<Integer> appAdd(int region, String name, int maxTenantsCount, int maxArticlesCount);
	
	/**
	 * 修改平台
	 * 
	 * @param appId
	 * @param region
	 * @param name
	 * @param maxTenantsCount
	 * @param maxArticlesCount
	 */
	Result<Void> appUpdate(int appId, int region, String name, int maxTenantsCount, int maxArticlesCount);
}
