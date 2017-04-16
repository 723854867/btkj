package org.btkj.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.AppMainPageInfo;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
import org.btkj.pojo.info.tips.MainTenantTips;
import org.btkj.pojo.info.tips.TenantTips;
import org.btkj.user.api.UserService;
import org.btkj.user.redis.EmployeeMapper;
import org.btkj.user.redis.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public User getUser(int uid) {
		return userMapper.getByKey(uid);
	}
	
	@Override
	public User getUserByEmployeeId(int employeeId) {
		Employee employee = employeeMapper.getByKey(employeeId);
		return null == employee ? null : userMapper.getByKey(employee.getUid());
	}
	
	@Override
	public User getUserByToken(Client ct, String token) {
		return userMapper.getUserByToken(ct, token);
	}
	
	@Override
	public Result<User> lockUserByToken(Client ct, String token) {
		return userMapper.lockUserByToken(ct, token);
	}
	
	@Override
	public void releaseUserLock(String lockId, int uid) {
		userMapper.releaseUserLock(uid, lockId);
	}
	
	@Override
	public Result<IMainPageInfo> mainPage(Client client, String token, Tenant tenant) {
		User user = userMapper.getUserByToken(client, token);
		if (null == user)
			return Result.result(Code.TOKEN_INVALID);
		
		switch (client) {
		case PC:
			AppMainPageInfo mainPageInfo = new AppMainPageInfo(user);
			int mainTid = userMapper.mainTenant(user.getUid());
			List<TenantTips> list = employeeMapper.tenantTipsList(user, mainTid);
			MainTenantTips mt = null;
			if (0 == mainTid && null != list && !list.isEmpty()) 
				mainTid = list.remove(0).getTid();
			if (0 != mainTid)
				mt = new MainTenantTips(mainTid);
			mainPageInfo.setTenant(mt);
			mainPageInfo.setOwnTenants(list);
			mainPageInfo.setAuditTenants(employeeMapper.auditTenantTipsList(user.getUid()));
			return Result.result(mainPageInfo);
		case MANAGER:
			return null;
		default:
			return null;
		}
	}
	
	@Override
	public Result<?> teamInfo(App app, Tenant tenant, String token) {
		User user = userMapper.getUserByToken(Client.APP, token);
		if (null == user)
			return Result.result(Code.USER_NOT_EXIST);
		return null;
	}
	
	@Override
	public Result<?> update(User user) {
		user.setUpdated(DateUtils.currentTime());
		try {
			userMapper.update(user);
		} catch (DuplicateKeyException e) {
			return Result.result(Code.IDENTITY_ALREADY_EXIST);
		}
		return Result.success();
	}
}
