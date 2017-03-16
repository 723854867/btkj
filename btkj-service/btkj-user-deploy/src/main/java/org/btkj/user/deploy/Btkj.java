package org.btkj.user.deploy;

import javax.annotation.Resource;

import org.btkj.user.persistence.Tx;
import org.rapid.util.common.Application;
import org.springframework.stereotype.Service;

@Service
public class Btkj extends Application {
	
	@Resource
	private Tx tx;
	
	@Override
	protected void start() {
	}
	
	@Override
	protected void stop() {
	}
}
