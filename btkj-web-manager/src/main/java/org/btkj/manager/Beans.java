package org.btkj.manager;

import org.btkj.user.api.TenantService;
import org.rapid.util.common.SpringContextUtil;

public interface Beans {

	final TenantService tenantService = SpringContextUtil.getBean("tenantService", TenantService.class);
}
