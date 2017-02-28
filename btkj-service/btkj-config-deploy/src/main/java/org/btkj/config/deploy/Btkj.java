package org.btkj.config.deploy;

import javax.annotation.Resource;

import org.btkj.config.redis.service.AppRedisService;
import org.btkj.config.redis.service.InsuranceRedisService;
import org.btkj.config.redis.service.RegionCityRedisService;
import org.btkj.config.redis.service.RegionDistrictRedisService;
import org.btkj.config.redis.service.RegionProvinceRedisService;
import org.rapid.util.common.Application;
import org.springframework.stereotype.Service;

@Service
public class Btkj extends Application {
	
	@Resource
	private AppRedisService appRedisService;
	@Resource
	private InsuranceRedisService insuranceRedisService;
	@Resource
	private RegionCityRedisService regionCityRedisService;
	@Resource
	private RegionDistrictRedisService regionDistrictRedisService;
	@Resource
	private RegionProvinceRedisService regionProvinceRedisService;

	@Override
	protected void start() {
		appRedisService.load();
		insuranceRedisService.load();
		regionCityRedisService.load();
		regionDistrictRedisService.load();
		regionProvinceRedisService.load();
	}
	
	@Override
	protected void stop() {
	}
}
