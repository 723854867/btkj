package org.btkj.common;

import org.btkj.courier.api.CourierService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.UserService;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.SpringContextUtil;
import org.rapid.util.common.cache.Cache;
import org.rapid.util.common.cache.CacheService;

@SuppressWarnings("unchecked")
public interface Beans {

	final Redis redis = SpringContextUtil.getBean("redis", Redis.class);
	final UserService userService = SpringContextUtil.getBean("userService", UserService.class);
	final CourierService courierService = SpringContextUtil.getBean("courierService", CourierService.class);
	final EmployeeService employeeService = SpringContextUtil.getBean("employeeService", EmployeeService.class);
	final CacheService<Cache<?, ?>> cacheService = SpringContextUtil.getBean("cacheService", CacheService.class);
}
