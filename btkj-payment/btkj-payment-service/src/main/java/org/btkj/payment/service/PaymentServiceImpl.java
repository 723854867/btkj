package org.btkj.payment.service;

import javax.annotation.Resource;

import org.btkj.payment.api.PaymentService;
import org.btkj.payment.mybatis.dao.AccountDao;
import org.btkj.payment.pojo.entity.Account;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	
	@Resource
	private AccountDao accountDao;

	@Override
	public Account account(int employeeId) {
		return accountDao.getByKey(employeeId);
	}
}
