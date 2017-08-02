package org.btkj.manager.action.tenant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.BizType;
import org.btkj.pojo.enums.InsuranceType;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.TenantPO.Mod;
import org.btkj.pojo.po.UserPO;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.pojo.entity.LogExploit;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.model.BonusScale;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.BonusScaleConfig;
import org.btkj.vehicle.pojo.entity.VehiclePolicy;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.CollectionUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.math.compare.ComparisonSymbol;
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
	protected Result<Void> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, EmployeeParam param) {
		if (!TenantPO.Mod.isScaleStatisticSupport(tenant.getMod()) || !TenantPO.Mod.isScaleRewardSupport(tenant.getMod()) )
			return BtkjConsts.RESULT.BONUS_SCALE_SETTINGS_ERROR;
		List<BonusScaleConfig> configs = vehicleManageService.bonusScaleConfigs(tenant.getTid());
		if (CollectionUtil.isEmpty(configs))
			return BtkjConsts.RESULT.BONUS_SCALE_SETTINGS_ERROR;
		
		int currentYear = DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, System.currentTimeMillis());
		int currentMonth = DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, System.currentTimeMillis());
		if (currentMonth == 0) {				// 如果当前是1月，则统计的时去年的12月的数据
			currentYear -= 1;
			currentMonth = 11;
		}
		int start = DateUtil.boundaryTimeOfMonth(currentYear, currentMonth, true);
		int end = DateUtil.boundaryTimeOfMonth(currentYear, currentMonth, false);
		int time = Integer.valueOf(DateUtil.getDate(DateUtil.YYYYMM, start));				// 当前年月 201405 格式
		if (tenant.getScaleRewardTime() != 0) {					// 需要判断是否已经统计过
			if (tenant.getScaleRewardTime() >= time)
				return BtkjConsts.RESULT.BONUS_SCALE_REWARDED;
		}
		Map<String, VehiclePolicy> policies = vehicleManageService.policies(tenant.getTid(), start, end);
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
			_recordScale(tenant, bs, policy, InsuranceType.COMMERCIAL, false);		// 商业-奖励孔径
			_recordScale(tenant, bs, policy, InsuranceType.COMPULSORY, true);			// 交强-统计口径	
			_recordScale(tenant, bs, policy, InsuranceType.COMPULSORY, false);		// 交强-奖励口径
		}
		exploits = statisticsService.recordLogExploits(exploits);
		for (BonusScale bs : map.values()) {
			int SCQuota = bs.getSCQuota();
			for (BonusScaleConfig config : configs) {
				ComparisonSymbol symbol = ComparisonSymbol.match(config.getComparison());
				if (null == symbol)
					continue;
				String[] params = config.getComparableValue().split(Consts.SYMBOL_UNDERLINE);
				if (!IntComparable.SINGLETON.compare(symbol, SCQuota, CollectionUtil.toIntegerArray(params)))
					continue;
				bs.setCmRate(config.getRate());
				break;
			}
		}
		if (map.isEmpty())
			return Consts.RESULT.OK;
		userManageService.calculateTeamExploits(time, tenant, map);
		return Consts.RESULT.OK;
	}
	
	private LogExploit _exploit(VehiclePolicy policy) {
		switch (policy.getMark()) {
		case NONE:
		case NOT_EXIST:
		case UNSUITABLE:
			logger.info("业务员归属存在异议的保单不参与业务统计");
			return null;
		default:
			LogExploit exploit = new LogExploit();
			exploit.setTid(policy.getTid());
			exploit.setAppId(policy.getAppId());
			exploit.setEmployeeId(policy.getSalesmanId());
			exploit.setType(BizType.VEHICLE_BONUS_SCALE.mark());
			exploit.setDetailType(policy.getBonusType().mark());
			exploit.setBizId(policy.get_id());
			exploit.setQuota(policy.quotaInCent());
			exploit.setYear(DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssueTime()));
			exploit.setMonth(DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssueTime()));
			exploit.setDay(DateUtil.dayOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssueTime()));
			exploit.setWeek(DateUtil.weekOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssueTime()));
			exploit.setSeason(DateUtil.season(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, policy.getIssueTime()));
			exploit.setCreated(policy.getIssueTime());
			return exploit;
		}
	}
	
	private void _recordScale(TenantPO tenant, BonusScale bs, VehiclePolicy policy, InsuranceType type, boolean statistic) {
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
