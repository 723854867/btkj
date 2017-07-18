package org.btkj.vehicle.api;

import java.util.List;

import org.btkj.pojo.vo.BonusSearcher;
import org.btkj.vehicle.pojo.model.BonusRouteView;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.rapid.util.common.message.Result;

/**
 * 佣金服务类
 * 
 * @author ahab
 */
public interface BonusService {
	
	/**
	 * 获取佣金路由结构表
	 * 
	 * @return
	 */
	BonusRouteView bonusRouteInfo();

	/**
	 * 获取车险可配置系数
	 * 
	 * @param searcher
	 * @return
	 */
	Result<List<VehicleCoefficientsInfo>> coefficients(BonusSearcher searcher);
	
	/**
	 * 佣金设置
	 * 
	 * @param searcher
	 * @return
	 */
	Result<Void> bonusSettings(BonusSearcher searcher);
}
