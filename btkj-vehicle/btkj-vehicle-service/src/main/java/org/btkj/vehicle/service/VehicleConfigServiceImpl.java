package org.btkj.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.vehicle.api.VehicleConfigService;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.redis.BonusManageConfigMapper;
import org.springframework.stereotype.Service;

@Service("vehicleConfigService")
public class VehicleConfigServiceImpl implements VehicleConfigService {
	
	@Resource
	private BonusManageConfigMapper bonusManageConfigMapper;

	@Override
	public List<BonusManageConfig> bonusManageConfigs(int tid) {
		return bonusManageConfigMapper.getByTid(tid);
	}
}
