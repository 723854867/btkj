package org.btkj.common;

import javax.annotation.Resource;

import org.btkj.common.cache.CacheService;
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
