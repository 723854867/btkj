package org.btkj.payment.service;

import javax.annotation.Resource;

import org.btkj.payment.api.PaymentService;
import org.btkj.payment.pojo.entity.Account;
import org.btkj.payment.redis.AccountMapper;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Resource
	private AccountMapper accountMapper;
	
	@Override
	public Account getAccountByEmployeeId(int employeeId) {
		return accountMapper.getByKey(employeeId);
	}
}
