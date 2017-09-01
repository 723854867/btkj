package org.btkj.vehicle.deploy;

import javax.annotation.Resource;

import org.btkj.vehicle.realm.poundage.Poundage;
import org.rapid.util.common.Application;
import org.rapid.util.common.cache.CacheService;
import org.springframework.stereotype.Component;

@Component
public class InitialHook extends Application {
	
	@Resource
	Poundage poundage;
	@Resource
	private CacheService<?> cacheService;

	@Override
	protected void start() {
		cacheService.init();			// 初始化缓存
		poundage.init();				// 初始化手续费配置
	}

	@Override
	protected void stop() {
		
	}
}
