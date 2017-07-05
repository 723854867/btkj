package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.user.pojo.submit.UserSearcher;
import org.btkj.user.redis.EmployeeMapper;
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
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public Result<Pager<UserPagingInfo>> userPaging(UserSearcher searcher) {
		return Result.result(userMapper.paging(searcher));
	}

	@Override
	public Result<Pager<TenantPagingInfo>> tenantPaging(TenantSearcher searcher) {
		return Result.result(tenantMapper.paging(searcher));
	}
	
	@Override
	public Result<Pager<EmployeePagingInfo>> employeePaging(EmployeeSearcher searcher) {
		if (null != searcher.getMobile()) {
			User user = userMapper.getUserByMobile(searcher.getAppId(), searcher.getMobile());
			if (null == user)
				return Result.result(Pager.EMPLTY);
			searcher.setUid(user.getUid());
			Pager<EmployeePagingInfo> pager = employeeMapper.paging(searcher);
			for (EmployeePagingInfo info : pager.getList())
				info.setName(user.getName());
			return Result.result(pager);
		}
		return Result.result(employeeMapper.paging(searcher));
	}
}
