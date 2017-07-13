package org.btkj.vehicle.api;

import java.util.List;

import org.btkj.vehicle.pojo.entity.BonusManageConfig;

public interface VehicleConfigService {

	/**
	 * 获取指定商户的管理佣金设置
	 * 
	 * @param tid
	 * @return
	 */
	List<BonusManageConfig> bonusManageConfigs(int tid);
}
