package org.btkj.user.redis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Employee;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.serializer.SerializeUtil;
import org.springframework.stereotype.Component;

/**
 * 专门用来控制用户的缓存加载状态
 * 
 * @author ahab
 */
@Component
public class UserCacheController {
	
	private static final String CACHE_VALUE	 = "v";
	private static final String TENANTS_LIST = "tl";

	@Resource
	private Redis redis;
	@Resource
	private EmployeeDao employeeDao;
	
	/**
	 * 获取用户拥有的所有代理公司：一般是保途 app 调用
	 * 
	 * @param uid
	 * @return
	 */
	public Set<Integer> tenants(int uid, int appId) {
		Set<Integer> set = null;
		if (!redis.hsetnx(RedisKeyGenerator.userCacheControllerKey(uid), TENANTS_LIST, CACHE_VALUE)) {
			Set<String> s = redis.hkeys(RedisKeyGenerator.userEmployeeKey(uid));
			if (null == s || s.isEmpty())
				return set;
			set = new HashSet<Integer>();
			for (String str : s)
				set.add(Integer.valueOf(str));
			return set;
		}
		List<Employee> employees = employeeDao.selectByUidAndAppId(uid, appId);
		if (employees.isEmpty())
			return null;
		set = new HashSet<Integer>();
		byte[][] params = new byte[employees.size() * 3 + 2][];
		int index = 0;
		params[index++] = SerializeUtil.RedisUtil.encode(RedisKeyGenerator.employeeDataKey());
		params[index++] = SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userEmployeeKey(uid));
		for (Employee employee : employees) {
			params[index++] = SerializeUtil.RedisUtil.encode(employee.getId());
			params[index++] = SerializeUtil.RedisUtil.encode(employee.getTid());
			params[index++] = SerializeUtil.ProtostuffUtil.serial(employee);
			set.add(employee.getTid());
		}
		redis.invokeLua(UserLuaCmd.REFRESH_EMPLOYEES, params);
		return set;
	}
}
