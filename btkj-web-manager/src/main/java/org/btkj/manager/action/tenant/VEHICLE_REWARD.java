package org.btkj.manager.action.tenant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.payment.api.PaymentManageService;
import org.btkj.payment.pojo.model.ScoreTips;
import org.btkj.pojo.VehicleUtil;
import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.entity.EmployeePO.Mod;
import org.btkj.pojo.enums.BizType;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.model.PolicySchema;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.pojo.entity.LogScore;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.BonusManageConfig;
import org.btkj.vehicle.pojo.enums.BonusManageConfigType;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;

/**
 * 奖励结算
 * 
 * @author ahab
 */
public class VEHICLE_REWARD extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private StatisticsService statisticsService;
	@Resource
	private PaymentManageService paymentManageService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		Result<Map<String, VehicleOrder>> result = vehicleManageService.orderRewardStandby(tenant.getTid());
		if (!result.isSuccess())
			return result;
		Map<String, VehicleOrder> orders = result.attach();
		if (CollectionUtil.isEmpty(orders))
			return Consts.RESULT.OK;
		Set<Integer> set = new HashSet<Integer>();
		for (VehicleOrder order : orders.values()) 
			set.add(order.getEmployeeId());
		Map<Integer, EmployeePO> employees = employeeService.employees(set);
		Map<Integer, LinkedList<Integer>> manageMap = new HashMap<Integer, LinkedList<Integer>>();
		int teamDepth = tenant.getTeamDepth();
		Map<String, BonusManageConfig> configs = null;
		if (teamDepth > 1) {								// 如果基层设置为小于 1 则不会有管理奖励
			set.clear();
			configs = vehicleManageService.bonusManageConfigs(tenant.getTid());
			for (EmployeePO temp : employees.values()) {
				LinkedList<Integer> list = VehicleUtil.relationEmployees(temp.getRelationPath(), teamDepth);
				if (!list.isEmpty()) {
					manageMap.put(temp.getId(), list);
					for (Integer id : list) {
						if (employees.containsKey(id))
							continue;
						set.add(id);
					}
				}
			}
			if (!set.isEmpty())
				employees.putAll(employeeService.employees(set));
		}
		Map<Integer, Map<BizType, ScoreTips>> scores = new HashMap<Integer, Map<BizType, ScoreTips>>();
		List<LogScore> logScores = new ArrayList<LogScore>();
		for (VehicleOrder order : orders.values()) {
			EmployeePO ep = employees.get(order.getEmployeeId());
			if (null != order.getBonus()) {					// 获取手续费
				LogScore logScore = _vehicleBonuslogScore(order);
				logScores.add(logScore);
				_scoreTips(ep.getId(), scores, logScore, BizType.VEHICLE_BONUS);
			}
			LinkedList<Integer> list = manageMap.get(order.getEmployeeId());
			if (!CollectionUtil.isEmpty(list)) {			// 获取管理费
				int depth = 2;
				while (!list.isEmpty()) {
					depth++;
					int employeeId = list.pollLast();
					EmployeePO temp = employees.get(employeeId);
					if ((temp.getMod() & Mod.BONUS_MANAGE.mark()) == Mod.BONUS_MANAGE.mark())		// 没有管理奖
						continue;
					LogScore logScore = _managerLogScore(configs, employeeId, order, InsuranceType.COMMERCIAL, depth);
					if (null != logScore) {
						logScores.add(logScore);
						_scoreTips(employeeId, scores, logScore, BizType.VEHICLE_BOUNS_MANAGE);
					}
					logScore = _managerLogScore(configs, employeeId, order, InsuranceType.COMPULSORY, depth);
					if (null != logScore) {
						logScores.add(logScore);
						_scoreTips(employeeId, scores, logScore, BizType.VEHICLE_BOUNS_MANAGE);
					}
				}
			}
		}
		if (!CollectionUtil.isEmpty(scores))
			paymentManageService.gainScores(scores);
		if (!CollectionUtil.isEmpty(logScores))
			statisticsService.recordLogScores(logScores);
		return Consts.RESULT.OK;
	}
	
	private LogScore _vehicleBonuslogScore(VehicleOrder order) {
		LogScore logScore = new LogScore();
		logScore.setTid(order.getTid());
		logScore.setUid(order.getUid());
		logScore.setAppId(order.getAppId());
		logScore.setEmployeeId(order.getEmployeeId());
		logScore.setBizId(order.get_id());
		logScore.setIncome(true);
		logScore.setType(BizType.VEHICLE_BONUS.mark());
		logScore.setQuota(VehicleUtil.getTotalQuotaInCent(order));
		int time = order.getCreated() * 1000;
		logScore.setYear(DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setMonth(DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setDay(DateUtil.dayOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setWeek(DateUtil.weekOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setSeason(DateUtil.season(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setCreated(time);
		return logScore;
	}
	
	private LogScore _vehicleManageBonuslogScore(VehicleOrder order, int employeeId) {
		LogScore logScore = new LogScore();
		logScore.setTid(order.getTid());
		logScore.setUid(order.getUid());
		logScore.setAppId(order.getAppId());
		logScore.setEmployeeId(employeeId);
		logScore.setBizId(order.get_id());
		logScore.setIncome(true);
		logScore.setType(BizType.VEHICLE_BONUS.mark());
		logScore.setQuota(VehicleUtil.getTotalQuotaInCent(order));
		int time = order.getCreated() * 1000;
		logScore.setYear(DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setMonth(DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setDay(DateUtil.dayOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setWeek(DateUtil.weekOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setSeason(DateUtil.season(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setCreated(order.getCreated());
		return logScore;
	}
	
	private LogScore _managerLogScore(Map<String, BonusManageConfig> configs, int employeeId, VehicleOrder order, InsuranceType type, int depth) {
		BonusManageConfigType manageConfigType = null;
		PolicySchema schema = order.getTips().getSchema();
		String quota = type == InsuranceType.COMMERCIAL ? schema.getCommercialTotal() : schema.getCompulsoryTotal();
		if (quota.equals("0"))
			return null;
		switch (order.getTips().getUsedType()) {
		case ORGAN:
		case HOME_USE:
		case ENTERPRISE:
		case NO_BIZ_TRUCK:
			manageConfigType = type == InsuranceType.COMMERCIAL ? BonusManageConfigType.COMMERCIAL_NO_PROFIT : BonusManageConfigType.COMPULSORY_NO_PROFIT;
			break;
		case LEASE:
		case CITY_BUS:
		case BIZ_TRUCK:
		case HIGHWAY_TRANSPORT:
			manageConfigType = type == InsuranceType.COMMERCIAL ? BonusManageConfigType.COMMERCIAL_PROFIT : BonusManageConfigType.COMPULSORY_PROTFIT;
			break;
		default:
			manageConfigType = type == InsuranceType.COMMERCIAL ? BonusManageConfigType.COMMERCIAL_OTHER : BonusManageConfigType.COMPULSORY_OTHER;
			break;
		}
		String key = order.getTid() + Consts.SYMBOL_UNDERLINE + manageConfigType.mark() + Consts.SYMBOL_UNDERLINE + depth;
		BonusManageConfig config = configs.get(key);
		if (null == config)
			return null;
		LogScore logScore = new LogScore();
		logScore.setTid(order.getTid());
		logScore.setUid(order.getUid());
		logScore.setAppId(order.getAppId());
		logScore.setEmployeeId(employeeId);
		logScore.setBizId(order.get_id());
		logScore.setIncome(true);
		logScore.setType(BizType.VEHICLE_BOUNS_MANAGE.mark());
		logScore.setDetailType(manageConfigType.mark());
		logScore.setQuota(new BigDecimal(quota).multiply(new BigDecimal(config.getRate())).divide(new BigDecimal(10)).setScale(0, RoundingMode.HALF_UP).intValue());
		int time = order.getCreated() * 1000;
		logScore.setYear(DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setMonth(DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setDay(DateUtil.dayOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setWeek(DateUtil.weekOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setSeason(DateUtil.season(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		logScore.setCreated(order.getCreated());
		return logScore;
	}
	
	private void _scoreTips(int employeeId, Map<Integer, Map<BizType, ScoreTips>> scores, LogScore logScore, BizType type) {
		Map<BizType, ScoreTips> map = scores.get(employeeId);
		if (null == map) {
			map = new HashMap<BizType, ScoreTips>();
			scores.put(employeeId, map);
		}
		ScoreTips tips = map.get(type);
		if (null == tips)
			tips = new ScoreTips(type, logScore.getQuota());
		else
			tips.setScore(tips.getScore() + logScore.getQuota());
	}
}
