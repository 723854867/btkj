package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.model.Pager;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.user.pojo.submit.UserSearcher;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private TenantMapper tenantMapper;
	
	@Override
	public Result<Pager<UserPagingInfo>> userPaging(UserSearcher searcher) {
		return Result.result(userMapper.paging(searcher));
	}

	@Override
	public Result<Pager<TenantPagingInfo>> tenantPaging(TenantSearcher searcher) {
		return Result.result(tenantMapper.paging(searcher));
	}
}
