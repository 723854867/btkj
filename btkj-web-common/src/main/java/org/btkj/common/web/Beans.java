package org.btkj.common.web;

import org.btkj.common.cache.CacheService;
import org.btkj.courier.api.CourierService;
import org.btkj.user.api.BtkjUserService;
import org.btkj.user.api.EmployeeService;
import org.btkj.user.api.IsolateUserService;
import org.btkj.user.api.UserService;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.SpringContextUtil;

public interface Beans {

	final Redis redis = SpringContextUtil.getBean("redis", Redis.class);
	final UserService userService = SpringContextUtil.getBean("userService", UserService.class);
	final CacheService cacheService = SpringContextUtil.getBean("cacheService", CacheService.class);
	final CourierService courierService = SpringContextUtil.getBean("courierService", CourierService.class);
	final EmployeeService employeeService = SpringContextUtil.getBean("employeeService", EmployeeService.class);
	final BtkjUserService btkjUserService = SpringContextUtil.getBean("btkjUserService", BtkjUserService.class);
	final IsolateUserService isolateUserService = SpringContextUtil.getBean("isolateUserService", IsolateUserService.class);
}
