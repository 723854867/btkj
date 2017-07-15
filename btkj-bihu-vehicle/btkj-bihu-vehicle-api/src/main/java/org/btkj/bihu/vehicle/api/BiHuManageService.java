package org.btkj.bihu.vehicle.api;

import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.rapid.util.common.message.Result;

public interface BiHuManageService {
	
	TenantConfig tenantConfig(int tid);
	
	Result<Void> tenantConfigAdd(int tid, String agent, String key);
	
	Result<Void> tenantConfigUpdate(int tid, String agent, String key);
	
	void tenantConfigDelete(int tid);
}
