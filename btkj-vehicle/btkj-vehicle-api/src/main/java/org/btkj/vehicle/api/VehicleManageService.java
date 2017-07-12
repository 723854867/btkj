package org.btkj.vehicle.api;

import java.util.List;

import org.btkj.pojo.entity.VehicleCoefficient;
import org.btkj.pojo.enums.vehicle.CoefficientType;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.BonusScaleConfigType;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.rapid.util.common.message.Result;
import org.rapid.util.math.compare.ComparisonSymbol;

public interface VehicleManageService {
	
	/**
	 * 获取车险系数
	 * 
	 * @param tid
	 * @return
	 */
	List<VehicleCoefficient> coefficients(int tid);
	
	/**
	 * 删除车险系数
	 * 
	 * @param type
	 * @param id
	 * @param tid
	 * @return
	 */
	Result<Void> coefficientDelete(int tid, CoefficientType type, int id);
	
	/**
	 * 新增车险系数
	 * 
	 * @param type
	 * @param symbol
	 * @param value
	 * @param name
	 * @return
	 */
	Result<Void> coefficientAdd(int tid, CoefficientType type, ComparisonSymbol symbol, String[] value, String name);
	
	/**
	 * 更新车险系数
	 * 
	 * @param type
	 * @param id
	 * @param symbol
	 * @param value
	 * @param name
	 * @return
	 */
	Result<Void> coefficientUpdate(int tid, CoefficientType type, int id, ComparisonSymbol symbol, String[] value, String name);
	
	/**
	 * 规模奖励配置
	 * 
	 * @param tid
	 * @return
	 */
	List<BonusScaleConfig> bonusScaleConfigs(int tid);
	
	/**
	 * 新增管理奖励配置项
	 * 
	 * @param tid
	 * @param type
	 * @param depth
	 * @param rate
	 * @return
	 */
	Result<Void> bonusManageConfigAdd(int tid, BonusScaleConfigType type, int depth, int rate);
	
	/**
	 * 更新管理奖励配置项
	 * 
	 * @param id
	 * @param rate
	 * @return
	 */
	Result<Void> bonusManageConfigUpdate(String id, int tid, int rate);
	
	/**
	 * 删除管理奖励配置项
	 * 
	 * @param id
	 * @return
	 */
	Result<Void> bonusManageConfigDelete(String id, int tid);
	
	/**
	 * 新增规模奖励配置项
	 * 
	 * @param tid
	 * @param type
	 * @param depth
	 * @param rate
	 * @return
	 */
	Result<Void> bonusScaleConfigAdd(int tid, BonusManageConfigType type, int rate, int min, int max);
	
	/**
	 * 更新规模奖励配置项
	 * 
	 * @param id
	 * @param rate
	 * @return
	 */
	Result<Void> bonusScaleConfigUpdate(int id, int tid, int rate, int min, int max);
	
	/**
	 * 删除规模奖励配置项
	 * 
	 * @param id
	 * @return
	 */
	Result<Void> bonusScaleConfigDelete(int id, int tid);
}
