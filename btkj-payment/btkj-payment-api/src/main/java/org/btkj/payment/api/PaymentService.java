package org.btkj.payment.api;

import org.btkj.pojo.entity.payment.Account;

public interface PaymentService {

	Account account(int employeeId);
}
