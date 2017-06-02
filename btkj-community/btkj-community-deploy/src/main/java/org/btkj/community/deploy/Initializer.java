package org.btkj.community.deploy;

import javax.annotation.Resource;

import org.btkj.community.redis.ArticleMapper;
import org.rapid.util.common.Application;
import org.springframework.stereotype.Component;

@Component("initializer")
public class Initializer extends Application {
	
	@Resource
	private ArticleMapper articleMapper;

	@Override
	protected void start() {
		articleMapper.init();
	}

	@Override
	protected void stop() {
		
	}
}
