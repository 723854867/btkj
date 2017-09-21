package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.entity.statistics.LogExploit;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.Tenant.Mod;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.entity.vehicle.BonusScaleConfig;
import org.btkj.pojo.entity.vehicle.VehiclePolicy;
import org.btkj.pojo.enums.ExploitType;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.model.BonusScale;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.user.api.UserManageService;
import org.btkj.vehicle.api.VehicleManageService;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.Comparison;
import org.rapid.util.math.compare.IntComparable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 规模奖励统计：统计完需要入库(只统计上个月的所有保单数据，如果某个月忘记统计了则数据默认会丢失)
 * 
 * @author ahab
 *
 */
public class VEHICLE_REWARD_SCALE extends EmployeeAction<EmployeeParam> {
	
	private static final Logger logger = LoggerFactory.getLogger(VEHICLE_REWARD_SCALE.class);
	
	@Resource
	private UserManageService userManageService;
	@Resource
	private StatisticsService statisticsService;
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<Void> execute(App app, User user, Tenant tenant, Employee employee, EmployeeParam param) {
		int currentYear = DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, System.currentTimeMillis());
		int currentMonth = DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, System.currentTimeMillis());
		if (currentMonth == 0) {				// 如果当前是1月，则统计的时去年的12月的数据
			currentYear -= 1;
			currentMonth = 11;
		}
		int start = DateUtil.boundaryTimeOfMonth(currentYear, currentMonth, true);
		int end = DateUtil.boundaryTimeOfMonth(currentYear, currentMonth, false);
		int time = Integer.valueOf(DateUtil.getDate(DateUtil.YYYYMM, start));				// 当前年月 201405 格式
		if (tenant.getScaleRewardTime() >= time)
				return BtkjConsts.RESULT.BONUS_SCALE_REWARDED;
		Map<String, VehiclePolicy> policies = vehicleManageService.policies(tenant.getTid(), start, end);
		if (!CollectionUtil.isEmpty(policies)) {
			List<LogExploit> exploits = new ArrayList<LogExploit>();
			Map<Integer, BonusScale> map = new HashMap<Integer, BonusScale>();
			for (VehiclePolicy policy : policies.values()) {
				LogExploit exploit = _exploit(policy);
				if (null == exploit)
					continue;
				exploits.add(exploit);
				
				BonusScale bs = map.get(exploit.getEmployeeId());
				if (null == bs) {
					bs = new BonusScale();
					bs.addPolicy(policy.get_id());
					bs.setEmployeeId(exploit.getEmployeeId());
					map.put(exploit.getEmployeeId(), bs);
				}
				_recordScale(tenant, bs, policy, InsuranceType.COMMERCIAL, true);			// 商业-统计口径
				_recordScale(tenant, bs, policy, InsuranceType.COMMERCIAL, false);			// 商业-奖励孔径
				_recordScale(tenant, bs, policy, InsuranceType.COMPULSORY, true);			// 交强-统计口径	
				_recordScale(tenant, bs, policy, InsuranceType.COMPULSORY, false);			// 交强-奖励口径
			}
			if (!CollectionUtil.isEmpty(exploits)) {
				exploits = statisticsService.recordLogExploits(exploits);
				List<BonusScaleConfig> configs = vehicleManageService.bonusScaleConfigs(tenant.getTid());
				for (BonusScale bs : map.values()) {
					int SCQuota = bs.getSCQuota();
					for (BonusScaleConfig config : configs) {
						String[] params = config.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
						if (!IntComparable.SINGLETON.compare(Comparison.match(config.getComparison()), SCQuota, CollectionUtil.toIntegerArray(params)))
							continue;
						bs.setCmRate(config.getRate());
						break;
					}
				}
				userManageService.calculateTeamExploits(time, tenant, map);
			}
		}
		return Consts.RESULT.OK;
	}
	
	private LogExploit _exploit(VehiclePolicy policy) {
		switch (policy.getMark()) {
		case NONE:
		case NOT_EXIST:
		case UNSUITABLE:
			logger.info("非保途平台保单不参与规模奖励计算");
			return null;
		default:
			LogExploit exploit = new LogExploit();
			exploit.setTid(policy.getTid());
			exploit.setAppId(policy.getAppId());
			exploit.setEmployeeId(policy.getSalesmanId());
			exploit.setType(ExploitType.VEHICLE.mark());
			exploit.setDetailType(policy.getBonusType().mark());
			exploit.setBizId(policy.get_id());
			exploit.setQuota(policy.commercialQuotaInCent() + policy.compulsoryQuotaInCent());
			exploit.setYear(DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setMonth(DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setDay(DateUtil.dayOfMonth(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setWeek(DateUtil.weekOfMonth(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setSeason(DateUtil.season(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setCreated(policy.getIssuanceTime());
			return exploit;
		}
	}
	
	private void _recordScale(Tenant tenant, BonusScale bs, VehiclePolicy policy, InsuranceType type, boolean statistic) {
		int quota = type == InsuranceType.COMMERCIAL ? policy.commercialQuotaInCent() : policy.compulsoryQuotaInCent();
		if (0 == quota)
			return;
		bs.setQuota(bs.getQuota() + quota);
		int mod = 0;
		switch (policy.getBonusType()) {
		case PC:
			if (type == InsuranceType.COMMERCIAL)
				mod = statistic ? Mod.SC_PC.mark() | Mod.SC_CM.mark() : Mod.RC_PC.mark() | Mod.SC_CM.mark();
			else 
				mod = statistic ? Mod.SC_PC.mark() | Mod.SC_CP.mark() : Mod.RC_PC.mark() | Mod.SC_CP.mark();
			break;
		case PT:
			if (type == InsuranceType.COMMERCIAL)
				mod = statistic ? Mod.SC_PT.mark() | Mod.SC_CM.mark() : Mod.RC_PT.mark() | Mod.SC_CM.mark();
			else 
				mod = statistic ? Mod.SC_PT.mark() | Mod.SC_CP.mark() : Mod.RC_PT.mark() | Mod.SC_CP.mark();
			break;
		case NPC:
			if (type == InsuranceType.COMMERCIAL)
				mod = statistic ? Mod.SC_NPC.mark() | Mod.SC_CM.mark() : Mod.RC_NPC.mark() | Mod.SC_CM.mark();
			else 
				mod = statistic ? Mod.SC_NPC.mark() | Mod.SC_CP.mark() : Mod.RC_NPC.mark() | Mod.SC_CP.mark();
			break;
		case NPT:
			if (type == InsuranceType.COMMERCIAL)
				mod = statistic ? Mod.SC_NPT.mark() | Mod.SC_CM.mark() : Mod.RC_NPT.mark() | Mod.SC_CM.mark();
			else 
				mod = statistic ? Mod.SC_NPT.mark() | Mod.SC_CP.mark() : Mod.RC_NPT.mark() | Mod.SC_CP.mark();
			break;
		default:
			if (type == InsuranceType.COMMERCIAL)
				mod = statistic ? Mod.SC_OTHER.mark() | Mod.SC_CM.mark() : Mod.RC_OTHER.mark() | Mod.SC_CM.mark();
			else 
				mod = statistic ? Mod.SC_OTHER.mark() | Mod.SC_CP.mark() : Mod.RC_OTHER.mark() | Mod.SC_CP.mark();
			break;
		}
		if ((tenant.getMod() & mod) != mod)
			return;
		if (statistic)
			bs.setSCQuota(bs.getSCQuota() + quota);
		else
			bs.setRCQuota(bs.getRCQuota() + quota);
	}
}
