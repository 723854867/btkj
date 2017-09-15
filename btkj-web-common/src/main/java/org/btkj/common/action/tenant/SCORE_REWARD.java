package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.RewardInfo;
import org.btkj.common.pojo.param.ScoreRewardParam;
import org.btkj.payment.api.PaymentService;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

/**
 * 积分奖励
 * 
 * @author ahab
 */
public class SCORE_REWARD extends EmployeeAction<ScoreRewardParam> {
	
	@Resource
	private PaymentService paymentService;
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<?> execute(App app, User user, Tenant tenant, Employee employee, ScoreRewardParam param) {
		switch (param.getType()) {
		case 1:
			return Result.result(statisticsService.scoreReward(employee.getId(), param.getBeginTime(), param.getEndTime()));
		default:
			return Result.result(new RewardInfo(paymentService.account(employee.getId())));
		}
	}
}
