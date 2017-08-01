package org.btkj.vehicle.api;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.VehicleBrand;
import org.btkj.pojo.po.VehicleCoefficient;
import org.btkj.pojo.po.VehicleDept;
import org.btkj.pojo.po.VehicleModel;
import org.btkj.pojo.po.VehicleOrder;
import org.btkj.pojo.vo.EmployeeTip;
import org.btkj.pojo.vo.JianJiePoliciesInfo;
import org.btkj.vehicle.pojo.BonusManageConfigType;
import org.btkj.vehicle.pojo.Lane;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.Route;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.btkj.vehicle.pojo.submit.VehiclePolicySearcher;
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
	 * 获取指定商户的管理佣金设置
	 * 
	 * @param tid
	 * @return
	 */
	Map<String, BonusManageConfig> bonusManageConfigs(int tid);
	
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
	 * 规模奖励配置
	 * 
	 * @param tid
	 * @return
	 */
	List<BonusScaleConfig> bonusScaleConfigs(int tid);
	
	/**
	 * 新增规模奖励配置项
	 * 
	 * @param tid
	 * @param rate
	 * @param symbol
	 * @param val
	 * @return
	 */
	Result<Integer> bonusScaleConfigAdd(int tid, int rate, ComparisonSymbol symbol, String[] val);
	
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
	 * 同步简捷保单
	 * 
	 * @return
	 */
	void jianJieSynchronize(EmployeePO employee, Map<Integer, EmployeeTip> employees, JianJiePoliciesInfo info);
	
	/**
	 * 出单：将简捷同步过来的保单所对应的订单全部设置为已出单
	 * 
	 * @param tid
	 */
	void vehicleOrderIssue(int tid);
	
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
	Result<Void> routeAdd(int tid, int insurerId, Lane lane, int jianJieId);
	
	/**
	 * 更新路由
	 * 
	 * @param key
	 * @param lane
	 * @return
	 */
	Result<Void> routeUpdate(String key, Lane lane, int jianJieId);
	
	/**
	 * 删除路由
	 * 
	 * @param key
	 */
	void routeDelete(String key);
	
	/**
	 * 汽车品牌列表
	 * 
	 * @return
	 */
	List<VehicleBrand> brands();
	
	/**
	 * 新增品牌
	 * 
	 * @param name
	 * @return
	 */
	Result<Integer> brandAdd(String name);
	
	/**
	 * 修改品牌
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	Result<Void> brandUpdate(int id, String name);
	
	/**
	 * 车系列表
	 * 
	 * @return
	 */
	List<VehicleDept> depts(int brandId);
	
	/**
	 * 新增车系
	 * 
	 * @param brandId
	 * @param name
	 * @return
	 */
	Result<Integer> deptAdd(int brandId, String name);
	
	/**
	 * 修改车系
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	Result<Void> deptUpdate(int id, String name);
	
	/**
	 * 厂牌型号列表
	 * 
	 * @param deptId
	 * @return
	 */
	List<VehicleModel> models(int deptId);
	
	/**
	 * 新增厂牌型号
	 * 
	 * @param deptId
	 * @param name
	 * @return
	 */
	Result<Integer> modelAdd(int deptId, String name);
	
	/**
	 * 修改厂牌型号
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	Result<Void> modelUpdate(int id, String name);
	
	/**
	 * 车险订单详情:只能查看
	 * 
	 * @param orderId
	 * @return
	 */
	Result<VehicleOrder> order(int tid, String orderId);
	
	/**
	 * 车险订单分页
	 * 
	 * @param searcher
	 * @return
	 */
	Pager<VehicleOrder> orders(VehicleOrderSearcher searcher);
	
	/**
	 * 保单详情
	 * 
	 * @param tid
	 * @return
	 */
	Result<VehiclePolicy> policy(int tid, String orderId);
	
	/**
	 * 获取商户指定时间内的保单
	 * 
	 * @param tid
	 * @param start
	 * @param end
	 * @return
	 */
	Map<String, VehiclePolicy> policies(int tid, int start, int end);
	
	/**
	 * 车险保单分页
	 * @param searcher
	 * @return
	 */
	Pager<VehiclePolicy> policies(VehiclePolicySearcher searcher);
	
	/**
	 * 车险订单结算准备，需要将所有已出单车险订单状态修改为正在结算中
	 * 
	 * @param tid
	 * @param stateMod
	 * @return
	 */
	Result<Map<String, VehicleOrder>> orderRewardStandby(int tid);
	
	/**
	 * 车险订单结算完成
	 * 
	 * @param tid
	 */
	void orderRewardComplete(int tid);
}
