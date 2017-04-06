package org.btkj.master;

import org.btkj.courier.api.CourierService;
import org.btkj.master.persistence.dao.AdministratorDao;
import org.btkj.master.service.realm.RoleService;
import org.btkj.user.api.TenantService;
import org.btkj.user.api.UserService;
import org.rapid.util.common.SpringContextUtil;
import org.rapid.util.common.cache.CacheService;

public interface Beans {
	
	final RoleService roleService = SpringContextUtil.getBean("roleService", RoleService.class);
	final AdministratorDao administratorDao = SpringContextUtil.getBean("administratorDao", AdministratorDao.class);

	final UserService userService = SpringContextUtil.getBean("userService", UserService.class);
	final CacheService<?> cacheService = SpringContextUtil.getBean("cacheService", CacheService.class);
	final TenantService tenantService = SpringContextUtil.getBean("tenantService", TenantService.class);
	final CourierService courierService = SpringContextUtil.getBean("courierService", CourierService.class);
}
