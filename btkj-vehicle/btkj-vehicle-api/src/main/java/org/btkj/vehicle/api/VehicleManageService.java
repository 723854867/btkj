package org.btkj.vehicle.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.entity.vehicle.BonusManageConfig;
import org.btkj.pojo.entity.vehicle.BonusScaleConfig;
import org.btkj.pojo.entity.vehicle.JianJieInsurer;
import org.btkj.pojo.entity.vehicle.TenantInsurer;
import org.btkj.pojo.entity.vehicle.VehiclePolicy;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.info.JianJiePoliciesInfo;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.vehicle.BonusManageConfigEditParam;
import org.btkj.pojo.param.vehicle.BonusScaleConfigEditParam;
import org.btkj.pojo.param.vehicle.JianJieInsurerEditParam;
import org.btkj.pojo.param.vehicle.TenantSetParam;
import org.btkj.pojo.param.vehicle.VehicleOrdersParam;
import org.btkj.pojo.param.vehicle.VehiclePoliciesParam;
import org.rapid.util.common.message.Result;

public interface VehicleManageService {
	
	/**
	 * 管理佣金列表
	 * 
	 * @param tid
	 * @return
	 */
	Map<String, BonusManageConfig> bonusManageConfigs(int tid);
	
	/**
	 * 管理佣金设置
	 * 
	 * @param param
	 * @return
	 */
	Result<Void> bonusManageConfigEdit(int tid, BonusManageConfigEditParam param);
	
	/**
	 * 规模奖励配置
	 * 
	 * @param tid
	 * @return
	 */
	List<BonusScaleConfig> bonusScaleConfigs(int tid);
	
	/**
	 * 团队规模奖励编辑
	 * 
	 * @param tid
	 * @param param
	 * @return
	 */
	Result<?> bonusScaleConfigEdit(int tid, BonusScaleConfigEditParam param);
	
	/**
	 * 同步简捷保单
	 * 
	 * @return
	 */
	Map<String, VehiclePolicy> jianJieSynchronize(EmployeePO employee, Map<Integer, EmployeeTip> employees, JianJiePoliciesInfo info);
	
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
	Pager<VehicleOrder> orders(VehicleOrdersParam param);
	
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
	 * 获取指定保单
	 * 
	 * @param ids
	 * @return
	 */
	Map<String, VehiclePolicy> policies(Collection<String> ids);
	
	/**
	 * 车险保单分页
	 * @param searcher
	 * @return
	 */
	Pager<VehiclePolicy> policies(VehiclePoliciesParam param);
	
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
	
	/**
	 * 获取商户的险企
	 * 
	 * @param tid
	 * @return
	 */
	Map<String, TenantInsurer> insurers(int tid);
	
	/**
	 * 商户车险险企设置
	 */
	void insurerEdit(TenantSetParam param);
	
	/**
	 * 获取指定商户开通的所有简捷险企
	 * 
	 * @param tid
	 * @return
	 */
	Map<Integer, JianJieInsurer> jianJieInsurers(int tid);
	
	/**
	 * 简捷险企编辑
	 * 
	 * @param param
	 * @return
	 */
	Result<?> jianJieInsurerEdit(JianJieInsurerEditParam param);
}
