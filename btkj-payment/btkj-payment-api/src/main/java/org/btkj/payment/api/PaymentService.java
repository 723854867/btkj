package org.btkj.payment.api;

import org.btkj.payment.pojo.entity.Account;

public interface PaymentService {

	Account account(int employeeId);
}
