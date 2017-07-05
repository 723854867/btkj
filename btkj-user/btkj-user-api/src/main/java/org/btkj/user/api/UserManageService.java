package org.btkj.user.api;

import org.btkj.pojo.model.Pager;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
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
}
