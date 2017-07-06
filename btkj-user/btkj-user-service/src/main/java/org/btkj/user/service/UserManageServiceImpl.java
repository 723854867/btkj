package org.btkj.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.model.Pager;
import org.btkj.user.api.UserManageService;
import org.btkj.user.mybatis.EntityGenerator;
import org.btkj.user.pojo.info.ApplyPagingInfo;
import org.btkj.user.pojo.info.EmployeePagingInfo;
import org.btkj.user.pojo.info.TenantPagingInfo;
import org.btkj.user.pojo.info.UserPagingInfo;
import org.btkj.user.pojo.submit.EmployeeSearcher;
import org.btkj.user.pojo.submit.TenantSearcher;
import org.btkj.user.pojo.submit.UserSearcher;
import org.btkj.user.redis.ApplyMapper;
import org.btkj.user.redis.BannerMapper;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.TenantMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private ApplyMapper applyMapper;
	@Resource
	private BannerMapper bannerMapper;
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
		Pager<EmployeePagingInfo> pager = null;
		if (null != searcher.getMobile()) {
			User user = userMapper.getUserByMobile(searcher.getAppId(), searcher.getMobile());
			if (null == user)
				return Result.result(Pager.EMPLTY);
			searcher.setUid(user.getUid());
			pager = employeeMapper.paging(searcher);
		} else 
			pager = employeeMapper.paging(searcher);
		
		Set<Integer> set = new HashSet<Integer>();
		for (EmployeePagingInfo info : pager.getList()) 
			set.add(info.getParentId());
		List<Employee> parents = employeeMapper.getWithinKey(new ArrayList<Integer>(set));
		for (EmployeePagingInfo info : pager.getList()) {
			for (Employee employee : parents) {
				if (employee.getId() == info.getParentId())
					info.setParentUid(employee.getUid());
			}
		}
		set.clear();
		for (EmployeePagingInfo info : pager.getList()) {
			set.add(info.getUid());
			set.add(info.getParentUid());
		}
		List<User> users = userMapper.getWithinKey(new ArrayList<Integer>(set));
		for (EmployeePagingInfo info : pager.getList()) {
			for (User user : users) {
				if (info.getUid() == user.getUid()) {
					info.setName(user.getName());
					info.setMobile(user.getMobile());
				}
				if (info.getParentUid() == user.getUid()) {
					info.setParentName(user.getName());
					info.setParentMobile(user.getMobile());
				}
			}
		}
		return Result.result(employeeMapper.paging(searcher));
	}
	
	@Override
	public Result<Pager<ApplyPagingInfo>> applyPaging(int tid, int page, int pageSize) {
		Pager<ApplyPagingInfo> pager = applyMapper.paging(tid, page, pageSize);
		Set<Integer> set = new HashSet<Integer>();
		for (ApplyPagingInfo info : pager.getList()) {
			set.add(info.getUid());
			set.add(info.getParentUid());
		}
		
		List<User> users = userMapper.getWithinKey(new ArrayList<Integer>(set));
		for (ApplyPagingInfo info : pager.getList()) {
			for (User user : users) {
				if (user.getUid() == info.getUid()) {
					info.setName(user.getName());
					info.setMobile(user.getMobile());
					info.setIdentity(user.getIdentity());
					info.setIdentityFace(user.getIdentityFace());
					info.setIdentityBack(user.getIdentityBack());
				}
				if (user.getUid() == info.getParentUid()) {
					info.setParentName(user.getName());
					info.setParentMobile(user.getMobile());
				}
			}
		}
		return Result.result(pager);
	}
	
	@Override
	public Result<Void> bannerAdd(int appId, int tid, int idx, String icon, String link) {
		Banner banner = EntityGenerator.newBanner(appId, tid, idx, icon, link);
		try {
			bannerMapper.insert(banner);
		} catch (DuplicateKeyException e) {
			return Consts.RESULT.FAILURE;
		}
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> bannerEdit(int id, String icon, String link) {
		Banner banner = bannerMapper.getByKey(id);
		if (null == banner)
			return BtkjConsts.RESULT.BANNER_NOT_EXIST;
		banner.setImage(icon);
		banner.setLink(link);
		banner.setUpdated(DateUtils.currentTime());
		bannerMapper.update(banner);
		return Consts.RESULT.OK;
	}
	
	@Override
	public Result<Void> bannerDelete(int id) {
		bannerMapper.delete(id);
		return Consts.RESULT.OK;
	}
}
