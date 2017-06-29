package org.btkj.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.submit.BonusSearcher;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.model.BonusRouteView;
import org.btkj.vehicle.model.VehicleCoefficientInfo;
import org.btkj.vehicle.rule.bonus.BonusManager;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("bonusService")
public class BonusServiceImpl implements BonusService {
	
	@Resource
	private BonusManager bonusManager;
	
	@Override
	public BonusRouteView bonusRouteInfo() {
		return bonusManager.bonusRouteInfo();
	}

	@Override
	public Result<List<VehicleCoefficientInfo>> coefficients(BonusSearcher searcher) {
		return bonusManager.coefficients(searcher);
	}
	
	@Override
	public Result<Void> bonusSettings(BonusSearcher searcher) {
		return bonusManager.bonusSettings(searcher);
	}
}
