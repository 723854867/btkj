package org.btkj.master;

import javax.annotation.Resource;

import org.rapid.util.common.cache.CacheService;
import org.springframework.stereotype.Service;

@Service("initialHook")
public class InitialHook implements org.btkj.web.util.InitialHook {
	
	@Resource
	private CacheService<?> cacheService;

	@Override
	public void start() {
		cacheService.init();
	}
}
