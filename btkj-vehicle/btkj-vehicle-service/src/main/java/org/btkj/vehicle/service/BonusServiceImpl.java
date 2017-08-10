package org.btkj.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.model.BonusRouteView;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientsParam;
import org.btkj.vehicle.rule.bonus.BonusManager;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("bonusService")
public class BonusServiceImpl implements BonusService {
	
	@Resource
	private BonusManager bonusManager;
	
	@Override
	public BonusRouteView bonusPoundageConfigs() {
		return bonusManager.bonusRouteInfo();
	}
	
	@Override
	public Result<Void> bonusPoundageEdit(BonusPoundageEditParam param) {
		return bonusManager.bonusPoundageEdit(param);
	}

	@Override
	public Result<List<VehicleCoefficientsInfo>> poundageCoefficients(PoundageCoefficientsParam param) {
		return bonusManager.poundageCoefficients(param);
	}
}
