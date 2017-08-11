package org.btkj.bihu.vehicle.api;

import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.bihu.vehicle.pojo.param.TenantConfigEditParam;
import org.rapid.util.common.message.Result;

public interface BiHuManageService {
	
	TenantConfig tenantConfig(int tid);
	
	Result<Void> tenantConfigEdit(TenantConfigEditParam param); 
}
