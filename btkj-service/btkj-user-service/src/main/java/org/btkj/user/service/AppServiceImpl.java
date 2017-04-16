package org.btkj.user.service;

import javax.annotation.Resource;

import org.btkj.pojo.entity.App;
import org.btkj.pojo.info.AppCreateInfo;
import org.btkj.pojo.model.AppCreateModel;
import org.btkj.user.api.AppService;
import org.btkj.user.persistence.Tx;
import org.btkj.user.redis.AppMapper;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("appService")
public class AppServiceImpl implements AppService {
	
	@Resource
	private Tx tx;
	@Resource
	private AppMapper appMapper;
	
	@Override
	public App getAppById(int appId) {
		return appMapper.getByKey(appId);
	}

	@Override
	public Result<AppCreateInfo> addApp(int region, String appName, int maxTenantsCount, int tenantRegion,
			String tenantName, String pwd, String mobile, String name, String identity) {
		AppCreateModel acm = tx.addApp(region, appName, maxTenantsCount, tenantRegion, tenantName, pwd, mobile, name, identity);
		AppCreateInfo aci = new AppCreateInfo();
		aci.setAppId(acm.getApp().getId());
		aci.setTid(acm.getTenant().getTid());
		aci.setUid(acm.getUser().getUid());
		return Result.result(aci);
	}
}
