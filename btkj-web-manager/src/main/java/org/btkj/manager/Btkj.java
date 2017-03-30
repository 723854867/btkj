package org.btkj.manager;

import javax.annotation.Resource;

import org.btkj.manager.web.cache.CacheService;
import org.rapid.util.common.Application;
import org.springframework.stereotype.Service;

@Service
public class Btkj extends Application {
	
	@Resource
	private CacheService cacheService;

	@Override
	protected void start() {
		cacheService.init();
	}

	@Override
	protected void stop() {
	}
}
