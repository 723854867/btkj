package org.btkj.manager;

import org.btkj.manager.web.cache.CacheService;
import org.btkj.user.api.TenantService;
import org.rapid.util.common.SpringContextUtil;

public interface Beans {

	final CacheService cacheService = SpringContextUtil.getBean("cacheService", CacheService.class);
	final TenantService tenantService = SpringContextUtil.getBean("tenantService", TenantService.class);
}
