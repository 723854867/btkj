package org.btkj.payment;

import org.btkj.pojo.entity.payment.Account;
import org.rapid.util.lang.DateUtil;

public class EntityGenerator {

	public static final Account newAccount(int employeeId) { 
		Account account = new Account();
		account.setEmployeeId(employeeId);
		account.setCreated(DateUtil.currentTime());
		return account;
	}
}
