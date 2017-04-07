package org.btkj.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.BtkjUtil;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.enums.ClientType;
import org.btkj.pojo.info.AppMainPageInfo;
import org.btkj.pojo.info.tips.MainTenantTips;
import org.btkj.pojo.info.tips.TenantTips;
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
	public User getUserByToken(ClientType ct, App app, String token) {
		return userMapper.getUserByToken(ct, app, token);
	}
	
	@Override
	public Result<User> lockUserByToken(ClientType ct, App app, String token) {
		return userMapper.lockUserByToken(ct, app.getId(), token);
	}
	
	@Override
	public void releaseUserLock(String lockId, int uid) {
		userMapper.releaseUserLock(uid, lockId);
	}
	
	@Override
	public Result<AppMainPageInfo> mainPage(App app, Tenant tenant, String token) {
		User user = userMapper.getUserByToken(ClientType.APP, app, token);
		if (null == user)
			return Result.result(Code.USER_NOT_EXIST);
		AppMainPageInfo mainPageInfo = new AppMainPageInfo(user);
		if (BtkjUtil.isBaoTuApp(app)) {
			int mainTid = userMapper.mainTenant(user.getUid());
			List<TenantTips> list = employeeMapper.tenantTipsList(BtkjConsts.APP_ID_BAOTU, user.getUid(), mainTid);
			MainTenantTips mt = 0 == mainTid ? 
					((null == list || list.isEmpty()) ? new MainTenantTips(BtkjConsts.TENANT_ID_BAOTU) : new MainTenantTips(list.remove(0).getTid())) 
							: new MainTenantTips(mainTid);
			mainPageInfo.setTenant(mt);
			mainPageInfo.setOwnTenants(list);
			mainPageInfo.setAuditTenants(employeeMapper.auditTenantTipsList(user.getUid()));
		}
		return Result.result(mainPageInfo);
	}
}
