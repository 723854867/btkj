package org.btkj.vehicle.api;

import java.util.List;

import org.btkj.vehicle.pojo.model.BonusRouteView;
import org.btkj.vehicle.pojo.model.VehicleCoefficientsInfo;
import org.btkj.vehicle.pojo.param.BonusPoundageEditParam;
import org.btkj.vehicle.pojo.param.PoundageCoefficientsParam;
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
	BonusRouteView bonusPoundageConfigs();
	
	/**
	 * 佣金设置
	 * 
	 * @param searcher
	 * @return
	 */
	Result<Void> bonusPoundageEdit(BonusPoundageEditParam param);
	
	/**
	 * 手续费系数列表
	 * 
	 * @param searcher
	 * @return
	 */
	Result<List<VehicleCoefficientsInfo>> poundageCoefficients(PoundageCoefficientsParam param);
}
