package org.btkj.payment.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.payment.mybatis.provider.AccountSQLProvider;
import org.btkj.payment.pojo.entity.Account;
import org.rapid.data.storage.mapper.DBMapper;

public interface AccountDao extends DBMapper<Integer, Account> {

	@Override
	@SelectProvider(type = AccountSQLProvider.class, method = "getByKey")
	Account getByKey(Integer key);
}
