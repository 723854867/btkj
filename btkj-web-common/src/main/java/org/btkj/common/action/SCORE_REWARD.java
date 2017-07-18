package org.btkj.common.action;

import javax.annotation.Resource;

import org.btkj.common.pojo.info.RewardInfo;
import org.btkj.payment.api.PaymentService;
import org.btkj.pojo.bo.EmployeeForm;
import org.btkj.pojo.enums.Client;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.web.util.Params;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.TenantAction;
import org.rapid.util.common.message.Result;
import org.rapid.util.exception.ConstConvertFailureException;

/**
 * 积分奖励
 * 
 * @author ahab
 */
public class SCORE_REWARD extends TenantAction {
	
	@Resource
	private PaymentService paymentService;
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<?> execute(Request request, Client client, EmployeeForm ef) {
		int type = request.getOptionalParam(Params.TYPE);
		switch (type) {
		case 0:									// 奖励首页：总计
			return Result.result(new RewardInfo(paymentService.getAccountByEmployeeId(ef.getEmployee().getId())));
		case 1:									// 按照时间统计积分雇员积分
			return Result.result(statisticsService.scoreReward(ef.getEmployee().getId(), request.getParam(Params.BEGIN_TIME), request.getParam(Params.END_TIME)));
		default:
			throw ConstConvertFailureException.errorConstException(Params.TYPE);
		}
	}
}
