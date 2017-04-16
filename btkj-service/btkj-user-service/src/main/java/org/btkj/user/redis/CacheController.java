package org.btkj.user.redis;

import java.util.Set;

import org.btkj.pojo.entity.Employee;
import org.btkj.user.persistence.dao.EmployeeDao;
import org.rapid.data.storage.redis.Redis;

/**
 * 专门用来控制用户的缓存加载状态
 * 
 * @author ahab
 */
public class CacheController {
	
	private static final String CACHE_VALUE	 = "v";
	private static final String TENANTS_LIST = "tl";

	private Redis redis;
	private EmployeeDao employeeDao;
	
	/**
	 * 获取用户拥有的所有代理公司：一般是保途 app 调用
	 * 
	 * @param uid
	 * @return
	 */
	public Set<Integer> tenantList(int uid) {
//		Set<Integer> set = null;
//		Object value = redis.invokeLua(UserLuaCmd.EMPLOYEE_LIST,
//				RedisKeyGenerator.userCacheControllerKey(uid), 
//				RedisKeyGenerator.userEmployeeKey(uid),
//				TENANTS_LIST, CACHE_VALUE);
//		if (value instanceof Long) {
//			List<Employee> employees = employeeDao.selectByUid(uid);
//			if (employees.isEmpty())
//				return Collections.EMPTY_SET;
//			set = new HashSet<Integer>();
//			byte[][] params = new byte[employees.size() * 3 + 2][];
//			int index = 0;
//			params[index++] = SerializeUtil.RedisUtil.encode(RedisKeyGenerator.employeeDataKey());
//			params[index++] = SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userEmployeeKey(uid));
//			for (Employee employee : employees) {
//				params[index++] = SerializeUtil.RedisUtil.encode(employee.getId());
//				params[index++] = SerializeUtil.RedisUtil.encode(employee.getTid());
//				params[index++] = SerializeUtil.ProtostuffUtil.serial(employee);
//				set.add(employee.getTid());
//			}
//			redis.invokeLua(UserLuaCmd.REFRESH_EMPLOYEES, params);
//			return set;
//		} else 
//			return CollectionUtils.toInt((Collection<String>) value);
		return null;
	}
	
	public void refreshEmployee(Employee employee) {
//		redis.invokeLua(UserLuaCmd.REFRESH_EMPLOYEE,
//				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userCacheControllerKey(employee.getUid())),
//				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.employeeDataKey()),
//				SerializeUtil.RedisUtil.encode(RedisKeyGenerator.userEmployeeKey(employee.getUid())),
//				SerializeUtil.RedisUtil.encode(TENANTS_LIST),
//				SerializeUtil.RedisUtil.encode(employee.getId()),
//				SerializeUtil.ProtostuffUtil.serial(employee),
//				SerializeUtil.RedisUtil.encode(employee.getTid()));
	}
	
	public Employee getEmployeeById(int id) { 
//		byte[] data = redis.hget(SerializeUtil.RedisUtil.encode(RedisKeyGenerator.employeeDataKey()), SerializeUtil.RedisUtil.encode(id));
//		return null == data ? null : SerializeUtil.ProtostuffUtil.deserial(data, Employee.class);
		return null;
	}
}
