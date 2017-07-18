package org.btkj.vehicle.service;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.vo.BonusSearcher;
import org.btkj.vehicle.api.BonusService;
import org.btkj.vehicle.pojo.model.BonusRouteView;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
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
	public Result<List<VehicleCoefficientsInfo>> coefficients(BonusSearcher searcher) {
		return bonusManager.coefficients(searcher);
	}
	
	@Override
	public Result<Void> bonusSettings(BonusSearcher searcher) {
		return bonusManager.bonusSettings(searcher);
	}
}
