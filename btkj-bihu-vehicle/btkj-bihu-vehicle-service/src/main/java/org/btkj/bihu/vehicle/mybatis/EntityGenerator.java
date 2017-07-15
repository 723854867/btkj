package org.btkj.bihu.vehicle.mybatis;

import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {
	
	public static final TenantConfig newTenantConfig(int tid, String agent, String key) { 
		TenantConfig config = new TenantConfig();
		config.setTid(tid);
		config.setAgent(agent);
		config.setKey(key);
		
		int time = DateUtil.currentTime();
		config.setCreated(time);
		config.setUpdated(time);
		return config;
	}
}
