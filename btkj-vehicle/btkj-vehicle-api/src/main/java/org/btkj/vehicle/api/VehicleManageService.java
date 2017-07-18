package org.btkj.vehicle.api;

import java.util.List;

import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.vo.JianJiePoliciesInfo;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.Route;
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
	Result<Void> bonusManageConfigAdd(int tid, BonusManageConfigType type, int depth, int rate);
	
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
	 * @param rate
	 * @param symbol
	 * @param val
	 * @return
	 */
	Result<Void> bonusScaleConfigAdd(int tid, int rate, ComparisonSymbol symbol, String[] val);
	
	/**
	 * 更新规模奖励配置项
	 * 
	 * @param id
	 * @param tid
	 * @param rate
	 * @param symbol
	 * @param val
	 * @return
	 */
	Result<Void> bonusScaleConfigUpdate(int id, int tid, int rate, ComparisonSymbol symbol, String[] val);
	
	/**
	 * 删除规模奖励配置项
	 * 
	 * @param id
	 * @return
	 */
	Result<Void> bonusScaleConfigDelete(int id, int tid);
	
	/**
	 * 获取指定的保单
	 * 
	 * @return
	 */
	void jianJieSynchronize(JianJiePoliciesInfo info);
	
	/**
	 * 获取车险路由设置
	 * 
	 * @param tid
	 * @return
	 */
	List<Route> routes(int tid);
	
	/**
	 * 新增路由
	 * 
	 * @param tid
	 * @param insurerId
	 * @param lane
	 * @return
	 */
	Result<Void> routeAdd(int tid, int insurerId, Lane lane);
	
	/**
	 * 更新路由
	 * 
	 * @param key
	 * @param lane
	 * @return
	 */
	Result<Void> routeUpdate(String key, Lane lane);
	
	/**
	 * 删除路由
	 * 
	 * @param key
	 */
	void routeDelete(String key);
}
