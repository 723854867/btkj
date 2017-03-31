package org.btkj.user.redis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Employee;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.lang.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * 专门用来控制用户的缓存加载状态
 * 
 * @author ahab
 */
@Component
@SuppressWarnings("unchecked")
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
	public Set<Integer> tenants(int uid) {
		Set<Integer> set = null;
		Object value = redis.invokeLua(UserLuaCmd.EMPLOYEE_LIST,
				RedisKeyGenerator.userCacheControllerKey(uid), 
				RedisKeyGenerator.userEmployeeKey(uid),
				TENANTS_LIST, CACHE_VALUE);
		if (value instanceof Long) {
			List<Employee> employees = employeeDao.selectByUid(uid);
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
		} else 
			return CollectionUtils.toInt((Set<String>) value);
	}
	
	public void refreshEmployee(Employee employee) {
		redis.invokeLua(UserLuaCmd.REFRESH_EMPLOYEE,
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userCacheControllerKey(employee.getUid())),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.employeeDataKey()),
				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userEmployeeKey(employee.getUid())),
				SerializeUtil.RedisUtil.encode(TENANTS_LIST),
				SerializeUtil.RedisUtil.encode(employee.getId()),
				SerializeUtil.ProtostuffUtil.serial(employee),
				SerializeUtil.RedisUtil.encode(employee.getTid()));
	}
}
