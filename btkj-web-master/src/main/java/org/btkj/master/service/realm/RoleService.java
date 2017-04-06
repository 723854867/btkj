package org.btkj.master.service.realm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.btkj.master.InternalConstants;
import org.btkj.master.pojo.info.LoginInfo;
import org.btkj.master.service.Role;
import org.rapid.util.common.RapidSecurity;
import org.rapid.util.common.cache.CacheService;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Resource
	private CacheService<?> cacheService;
	private Map<String, Role> onlines = new ConcurrentHashMap<String, Role>();
	private Map<Integer, String> tokens = new ConcurrentHashMap<Integer, String>();
	
	public Result<?> login(int id, String pwd) { 
		Role role = cacheService.getById(InternalConstants.ROLE_CACHE_NAME, id);
		if (null == role)
			return Result.result(Code.USER_NOT_EXIST);
		if (!role.tryLock())
			return Result.result(Code.USER_STATUS_CHANGED);
		
		try {
			if (!role.getPwd().equals(pwd))
				return Result.result(Code.PWD_ERROR);
			String token = RapidSecurity.encodeToken(String.valueOf(id));
			String old = tokens.putIfAbsent(id, token);
			if (null != old)
				onlines.remove(old);
			
			role.login();
			onlines.put(token, role);
			return Result.result(new LoginInfo(token, role));
		} finally {
			role.unlock();
		}
	}
	
	public Role getByToken(String token) {
		return onlines.get(token);
	}
}
