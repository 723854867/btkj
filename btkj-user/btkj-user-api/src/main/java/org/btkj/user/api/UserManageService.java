package org.btkj.user.api;

import java.util.Set;

import org.btkj.pojo.model.Pager;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.submit.EmployeeSearcher;
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
	 * 商家设置
	 * 
	 * @param tid
	 * @param nonAutoBind
	 * @return
	 */
	Result<Void> tenantSetting(int tid, Set<Long> nonAutoBind);
}
