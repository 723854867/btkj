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
import org.btkj.pojo.enums.VehicleBonusType;
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
				LogExploit cm = _exploit(policy, InsuranceType.COMMERCIAL);
				LogExploit cp = _exploit(policy, InsuranceType.COMPULSORY);
				if (null == cm && null == cp)
					continue;
				exploits.add(cm);
				exploits.add(cp);
				
				BonusScale bs = map.get(policy.getSalesmanId());
				if (null == bs) {
					bs = new BonusScale();
					bs.addPolicy(policy.get_id());
					bs.setEmployeeId(policy.getSalesmanId());
					map.put(policy.getSalesmanId(), bs);
				}
				if (null != cm)
					_recordScale(tenant, bs, cm, InsuranceType.COMMERCIAL);			// 商业
				if (null != cp)
					_recordScale(tenant, bs, cp, InsuranceType.COMPULSORY);			// 交强
			}
			if (!CollectionUtil.isEmpty(exploits)) {
				exploits = statisticsService.recordLogExploits(exploits);
				List<BonusScaleConfig> configs = vehicleManageService.bonusScaleConfigs(tenant.getTid());
				for (BonusScale bs : map.values()) {
					int SCQuota = bs.getSCCMQuota() + bs.getSCCPQuota();
					for (BonusScaleConfig config : configs) {
						String[] params = config.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
						if (!IntComparable.SINGLETON.compare(Comparison.match(config.getComparison()), SCQuota, CollectionUtil.toIntegerArray(params)))
							continue;
						bs.setCmRate(config.getRate());
						bs.setCpRate(config.getRate());
						break;
					}
				}
				userManageService.calculateTeamExploits(time, tenant, map);
			}
		}
		return Consts.RESULT.OK;
	}
	
	private LogExploit _exploit(VehiclePolicy policy, InsuranceType type) {
		switch (policy.getMark()) {
		case NONE:
		case NOT_EXIST:
		case UNSUITABLE:
			logger.info("非保途平台保单不参与规模奖励计算");
			return null;
		default:
			LogExploit exploit = new LogExploit();
			
			switch (type) {
			case COMMERCIAL:
				if (null == policy.getCommercialDetail() || 0 == policy.commercialQuotaInCent())
					return null;
				exploit.setType(ExploitType.VEHICLE_COMMERCIAL.mark());
				exploit.setQuota(policy.commercialQuotaInCent());
				break;
			case COMPULSORY:
				if (null == policy.getCompulsoryDetail() || 0 == policy.compulsoryQuotaInCent())
					return null;
				exploit.setType(ExploitType.VEHICLE_COMPULSORY.mark());
				exploit.setQuota(policy.compulsoryQuotaInCent());
				break;
			default:
				return null;
			}
			exploit.setTid(policy.getTid());
			exploit.setAppId(policy.getAppId());
			exploit.setEmployeeId(policy.getSalesmanId());
			exploit.setDetailType(policy.getBonusType().mark());
			exploit.setBizId(policy.get_id());
			exploit.setYear(DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setMonth(DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setDay(DateUtil.dayOfMonth(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setWeek(DateUtil.weekOfMonth(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setSeason(DateUtil.season(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssuanceTime()));
			exploit.setCreated(policy.getIssuanceTime());
			return exploit;
		}
	}
	
	private void _recordScale(Tenant tenant, BonusScale bs, LogExploit exploit, InsuranceType type) {
		bs.setQuota(bs.getQuota() + exploit.getQuota());
		int smod = 0;
		int rmod = 0;
		switch (VehicleBonusType.match(exploit.getDetailType())) {
		case PC:
			if (type == InsuranceType.COMMERCIAL) {
				smod = Mod.SC_PC.mark() | Mod.SC_CM.mark();
				rmod = Mod.RC_PC.mark() | Mod.RC_CM.mark();
			} else {
				smod = Mod.SC_PC.mark() | Mod.SC_CP.mark();
				rmod = Mod.RC_PC.mark() | Mod.RC_CP.mark();
			}
			break;
		case PT:
			if (type == InsuranceType.COMMERCIAL) {
				smod = Mod.SC_PT.mark() | Mod.SC_CM.mark();
				rmod = Mod.RC_PT.mark() | Mod.RC_CM.mark();
			} else {
				smod = Mod.SC_PT.mark() | Mod.SC_CP.mark();
				rmod = Mod.RC_PT.mark() | Mod.RC_CP.mark();
			}
			break;
		case NPC:
			if (type == InsuranceType.COMMERCIAL) {
				smod = Mod.SC_NPC.mark() | Mod.SC_CM.mark();
				rmod = Mod.RC_NPC.mark() | Mod.RC_CM.mark();
			} else {
				smod = Mod.SC_NPC.mark() | Mod.SC_CP.mark();
				rmod = Mod.RC_NPC.mark() | Mod.RC_CP.mark();
			}
			break;
		case NPT:
			if (type == InsuranceType.COMMERCIAL) {
				smod = Mod.SC_NPT.mark() | Mod.SC_CM.mark();
				rmod = Mod.RC_NPT.mark() | Mod.RC_CM.mark();
			} else {
				smod = Mod.SC_NPT.mark() | Mod.SC_CP.mark();
				rmod = Mod.RC_NPT.mark() | Mod.RC_CP.mark();
			}
			break;
		default:
			if (type == InsuranceType.COMMERCIAL) {
				smod = Mod.SC_OTHER.mark() | Mod.SC_CM.mark();
				rmod = Mod.RC_OTHER.mark() | Mod.RC_CM.mark();
			} else {
				smod = Mod.SC_OTHER.mark() | Mod.SC_CP.mark();
				rmod = Mod.RC_OTHER.mark() | Mod.RC_CP.mark();
			}
			break;
		}
		InsuranceType it = InsuranceType.match(exploit.getType());
		switch (it) {
		case COMMERCIAL:
			if ((tenant.getMod() & smod) == smod) 
				bs.setSCCMQuota(bs.getSCCMQuota() + exploit.getQuota());
			if ((tenant.getMod() & rmod) == rmod)
				bs.setRCCMQuota(bs.getSCCMQuota() + exploit.getQuota());
			break;
		case COMPULSORY:
			if ((tenant.getMod() & smod) == smod) 
				bs.setSCCPQuota(bs.getSCCMQuota() + exploit.getQuota());
			if ((tenant.getMod() & rmod) == rmod)
				bs.setRCCPQuota(bs.getSCCMQuota() + exploit.getQuota());
			break;
		default:
			break;
		}
	}
}
