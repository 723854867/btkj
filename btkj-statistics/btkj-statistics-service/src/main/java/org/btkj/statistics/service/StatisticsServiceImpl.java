package org.btkj.statistics.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.btkj.pojo.config.GlobalConfigContainer;
import org.btkj.pojo.entity.statistics.LogExploit;
import org.btkj.pojo.entity.statistics.LogScore;
import org.btkj.pojo.entity.statistics.StatisticAct;
import org.btkj.pojo.entity.statistics.StatisticPolicy;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.enums.ActType;
import org.btkj.pojo.info.statistics.PolicyStatisticInfo;
import org.btkj.pojo.info.statistics.Report_1_Info;
import org.btkj.pojo.model.Exploit;
import org.btkj.pojo.model.Exploits;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.statistics.StatisticActsParam;
import org.btkj.pojo.param.statistics.StatisticPoliciesParam;
import org.btkj.pojo.param.statistics.StatisticScoreParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.mybatis.dao.LogExploitDao;
import org.btkj.statistics.mybatis.dao.LogScoreDao;
import org.btkj.statistics.mybatis.dao.StatisticActDao;
import org.btkj.statistics.mybatis.dao.StatisticPolicyDao;
import org.btkj.statistics.redis.KeyGenerator;
import org.rapid.data.storage.redis.Redis;
import org.rapid.util.common.Consts;
import org.rapid.util.common.enums.TimeType;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.lang.DateUtil;
import org.rapid.util.lang.StringUtil;
import org.springframework.stereotype.Service;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	
	@Resource
	private Redis redis;
	@Resource
	private LogScoreDao logScoreDao;
	@Resource
	private LogExploitDao logExploitDao;
	@Resource
	private StatisticActDao statisticActDao;
	@Resource
	private StatisticPolicyDao statisticPolicyDao;
	
	@Override
	public List<Exploit> exploits(int employeeId, int begin, int end, int typeMod, TimeType timeType) {
		return logExploitDao.exploits(employeeId, begin, end, typeMod, timeType.name().toLowerCase());
	}
	
	@Override
	public Exploits multiExploits(List<Integer> employeeIds, int begin, int end, int bizTypeMod) {
		List<Exploit> employees = logExploitDao.multiExploits(employeeIds, begin, end, bizTypeMod);
		double performance = logExploitDao.totalExploit(employeeIds, begin, end, bizTypeMod);
		return new Exploits(performance, employees);
	}
	
	@Override
	public Pager<LogScore> scores(StatisticScoreParam param) {
		int total = logScoreDao.total(param);
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate(total);
		return new Pager<LogScore>(total, logScoreDao.scores(param));
	}
	
	@Override
	public void recordLogScores(List<LogScore> list) {
		logScoreDao.batchInsert(list);
	}
	
	@Override
	public void logScore(LogScore logScore) {
		logScoreDao.insert(logScore);
	}
	
	@Override
	public List<LogExploit> recordLogExploits(List<LogExploit> list) {
		logExploitDao.batchInsert(list);
		return list;
	}
	
	@Override
	public void statisticPolicies(Collection<StatisticPolicy> policies) {
		statisticPolicyDao.replace(policies);
	}
	
	@Override
	public Pager<PolicyStatisticInfo> statsiticPolicies(StatisticPoliciesParam param) {
		int total = statisticPolicyDao.statisticPoliciesTotal(param);
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate(total);
		List<PolicyStatisticInfo> list = statisticPolicyDao.statisticPolicies(param);
		return new Pager<PolicyStatisticInfo>(total, list);
	}
	
	@Override
	public void quoteRecord(Employee employee, String vin) {
		StatisticAct act = new StatisticAct();
		act.setAppId(employee.getAppId());
		act.setTid(employee.getTid());
		act.setUid(employee.getUid());
		act.setEmployeeId(employee.getId());
		act.setType(ActType.VEHICLE_QUOTE_SUCCESS.mark());

		int time = DateUtil.currentTime();
		act.setCreated(time);
		act.setYear(DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		act.setMonth(DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		act.setDay(DateUtil.dayOfMonth(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		act.setWeek(DateUtil.weekOfMonth(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		act.setSeason(DateUtil.season(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
		boolean flag = redis.hputJsonIfExpire(KeyGenerator.vehiclQuoteRecordKey(), employee.getId() + Consts.SYMBOL_UNDERLINE + vin, 
				SerializeUtil.JsonUtil.GSON.toJson(act), GlobalConfigContainer.getGlobalConfig().getQuoteActExpires());
		if (!flag)
			return;
		statisticActDao.insert(act);
	}
	
	@Override
	public Pager<Report_1_Info> report_1(StatisticActsParam param) {
		switch (param.getTimeType()) {
		case HOUR:
		case SECOND:
		case MINUTES:
		case NANOSECOND:
		case MILLISECOND:
			param.setTimeType(TimeType.MONTH);
			break;
		default:
			break;
		}
		Map<String, Report_1_Info> map = new HashMap<String, Report_1_Info>();
		int total = statisticActDao.report_1_total(param);
		if (0 != total) {
			param.calculate(total);
			List<StatisticAct> list = statisticActDao.report_1(param);
			for (StatisticAct act : list) {
				String key = StringUtil.underlineLink(act.getEmployeeId(), act.getYear(), act.getMonth(), act.getDay(), act.getWeek(), act.getSeason());
				Report_1_Info info = map.get(key);
				if (null == info) {
					info = new Report_1_Info();
					info.setUid(act.getUid());
					info.setEmployeeId(act.getEmployeeId());
					map.put(key, info);
				}
				info.addStatisticAct(act);
			}
		}
		StatisticPoliciesParam param2 = new StatisticPoliciesParam(param);
		int total1 = statisticPolicyDao.statisticPoliciesTotal(param2);
		if (0 != total) {
			param2.calculate(total1);
			param2.setGroupMod(StatisticPoliciesParam.GroupType.EMPLOYEE_ID.mark() | StatisticPoliciesParam.GroupType.UID.mark());
			List<PolicyStatisticInfo> list = statisticPolicyDao.statisticPolicies(param2);
			for (PolicyStatisticInfo info : list) {
				String key = StringUtil.underlineLink(info.getEmployeeId(), info.getYear(), info.getMonth(), info.getDay(), info.getWeek(), info.getSeason());
				Report_1_Info temp = map.get(key);
				if (null == temp) {
					temp = new Report_1_Info();
					temp.setEmployeeId(info.getEmployeeId());
					map.put(key, temp);
				}
				temp.addStatisticPolicy(info);
			}
		}
		return new Pager<Report_1_Info>(Math.max(total, total1), new ArrayList<Report_1_Info>(map.values()));
	}
}
