package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.info.AppMainPageInfo;
import org.btkj.user.api.UserService;
import org.btkj.user.redis.mapper.EmployeeMapper;
import org.btkj.user.redis.mapper.UserMapper;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
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
	public Result<AppMainPageInfo> mainPage(App app, Tenant tenant, String token) {
		AppMainPageInfo mainPageInfo = new AppMainPageInfo(null);
//		if (BtkjUtil.isMultiApp(app)) {
//			int mainTid = userMapper.mainTenant(user.getUid());
//			List<TenantTips> list = employeeMapper.tenantTipsList(app.getId(), user.getUid(), mainTid);
//			MainTenantTips mt = null;
//			if (0 == mainTid && null != list && !list.isEmpty()) 
//				mainTid = list.remove(0).getTid();
//			if (0 != mainTid)
//				mt = new MainTenantTips(mainTid);
//			mainPageInfo.setTenant(mt);
//			mainPageInfo.setOwnTenants(list);
//			mainPageInfo.setAuditTenants(employeeMapper.auditTenantTipsList(user.getUid()));
//		}
		return Result.result(mainPageInfo);
	}
	
	@Override
	public Result<?> teamInfo(App app, Tenant tenant, String token) {
		User user = userMapper.getUserByToken(Client.APP, token);
		if (null == user)
			return Result.result(Code.USER_NOT_EXIST);
		return null;
	}
}
