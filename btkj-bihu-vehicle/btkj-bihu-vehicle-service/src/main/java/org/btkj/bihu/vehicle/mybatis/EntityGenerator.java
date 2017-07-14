package org.btkj.bihu.vehicle.mybatis;

import org.btkj.bihu.vehicle.pojo.entity.BiHuArea;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.pojo.entity.Region;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {
	
	public static final BiHuArea newCity(Region region, int cid, String name) {
		BiHuArea city = new BiHuArea();
		city.setCid(cid);
		city.setCode(region.getId());
		city.setName(name);
		
		int time = DateUtil.currentTime();
		city.setCreated(time);
		city.setUpdated(time);
		return city;
	}
	
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
