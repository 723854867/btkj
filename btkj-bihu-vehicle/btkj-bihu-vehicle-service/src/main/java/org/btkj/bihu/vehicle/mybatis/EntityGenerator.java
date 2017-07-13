package org.btkj.bihu.vehicle.mybatis;

import org.btkj.bihu.vehicle.pojo.entity.BiHuArea;
import org.btkj.bihu.vehicle.pojo.entity.BiHuInsurer;
import org.btkj.bihu.vehicle.pojo.entity.TenantConfig;
import org.btkj.pojo.entity.Region;
import org.rapid.util.lang.DateUtils;

public class EntityGenerator {
	
	public static final BiHuArea newCity(Region region, int cid, String name) {
		BiHuArea city = new BiHuArea();
		city.setCid(cid);
		city.setCode(region.getId());
		city.setName(name);
		
		int time = DateUtils.currentTime();
		city.setCreated(time);
		city.setUpdated(time);
		return city;
	}
	
	public static final BiHuInsurer newInsurer(int code, String name) {
		BiHuInsurer insurer = new BiHuInsurer();
		insurer.setCode(code);
		insurer.setName(name);
		
		int time = DateUtils.currentTime();
		insurer.setCreated(time);
		insurer.setUpdated(time);
		return insurer;
	}
	
	public static final TenantConfig newTenantConfig(int tid, String agent, String key) { 
		TenantConfig config = new TenantConfig();
		config.setTid(tid);
		config.setAgent(agent);
		config.setKey(key);
		
		int time = DateUtils.currentTime();
		config.setCreated(time);
		config.setUpdated(time);
		return config;
	}
}
