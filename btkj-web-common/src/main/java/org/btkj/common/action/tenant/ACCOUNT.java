package org.btkj.common.action.tenant;

import javax.annotation.Resource;

import org.btkj.payment.api.PaymentService;
import org.btkj.pojo.entity.payment.Account;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.Employee;
import org.btkj.pojo.entity.user.Tenant;
import org.btkj.pojo.entity.user.User;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.statistics.api.StatisticsService;
import org.btkj.web.util.action.EmployeeAction;
import org.rapid.util.common.message.Result;

/**
 * 积分奖励
 * 
 * @author ahab
 */
public class ACCOUNT extends EmployeeAction<EmployeeParam> {
	
	@Resource
	private PaymentService paymentService;
	@Resource
	private StatisticsService statisticsService;

	@Override
	protected Result<Account> execute(App app, User user, Tenant tenant, Employee employee, EmployeeParam param) {
		return Result.result(paymentService.account(employee.getId()));
	}
}
