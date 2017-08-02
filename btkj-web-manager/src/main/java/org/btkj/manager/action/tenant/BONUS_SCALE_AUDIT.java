package org.btkj.manager.action.tenant;

import java.util.Locale;

import javax.annotation.Resource;

import org.btkj.manager.action.EmployeeAction;
import org.btkj.manager.pojo.param.BonusScaleAuditParam;
import org.btkj.payment.api.PaymentManageService;
import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.enums.BizType;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.EmployeePO;
import org.btkj.pojo.po.TenantPO;
import org.btkj.pojo.po.UserPO;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.statistics.pojo.entity.LogScore;
import org.btkj.user.api.UserManageService;
import org.btkj.user.pojo.model.BonusScale;
import org.rapid.util.common.Consts;
import org.rapid.util.common.message.Result;
import org.rapid.util.lang.DateUtil;

public class BONUS_SCALE_AUDIT extends EmployeeAction<BonusScaleAuditParam> {
	
	@Resource
	private UserManageService userManageService;
	@Resource
	private StatisticsService statisticsService;
	@Resource
	private PaymentManageService paymentManageService;
	
	@Override
	protected Result<?> execute(AppPO app, UserPO user, TenantPO tenant, EmployeePO employee, BonusScaleAuditParam param) {
		Result<BonusScale> result = userManageService.bonusScaleAudit(tenant.getTid(), param.getKey(), param.isAggree());
		if (!result.isSuccess() || !param.isAggree())
			return result;
		BonusScale bonusScale = result.attach();
		int rate = bonusScale.getCmRate();
		if (0 == rate)
			rate = bonusScale.getCpRate();
		int quota = rate * bonusScale.getQuota() / 1000;
		if (0 != quota) {
			Employee ep = employeeService.employee(bonusScale.getEmployeeId());
			paymentManageService.gainScore(bonusScale.getEmployeeId(), quota, BizType.VEHICLE_BONUS_SCALE);
			LogScore logScore = new LogScore();
			logScore.setEmployeeId(ep.getId());
			logScore.setUid(ep.getUid());
			logScore.setTid(ep.getTid());
			logScore.setAppId(ep.getAppId());
			logScore.setType(BizType.VEHICLE_BONUS_SCALE.mark());
			logScore.setBizId(bonusScale.get_id());
			logScore.setQuota(quota);
			logScore.setIncome(true);
			int time = DateUtil.currentTime();
			logScore.setYear(DateUtil.year(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
			logScore.setMonth(DateUtil.month(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
			logScore.setDay(DateUtil.dayOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
			logScore.setWeek(DateUtil.weekOfYear(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
			logScore.setSeason(DateUtil.season(DateUtil.TIMEZONE_GMT_8, Locale.CHINA, time));
			logScore.setCreated(time);
			statisticsService.logScore(logScore);
		}
		return Consts.RESULT.OK;
	}
}
