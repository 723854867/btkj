package org.btkj.payment.mybatis.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.payment.mybatis.provider.AccountSQLProvider;
import org.btkj.payment.pojo.entity.Account;
import org.rapid.data.storage.mapper.DBMapper;

public interface AccountDao extends DBMapper<Integer, Account> {
	
	@InsertProvider(type = AccountSQLProvider.class, method = "replace")
	void replace(Map<Integer, Account> accounts);
	
	@Override
	@SelectProvider(type = AccountSQLProvider.class, method = "getByKey")
	Account getByKey(Integer key);
	
	@SelectProvider(type = AccountSQLProvider.class, method = "getByKeyForUpdate")
	Account getByKeyForUpdate(Integer key);
	
	@MapKey("employeeId")
	@SelectProvider(type = AccountSQLProvider.class, method = "getByKeysForUpdate")
	Map<Integer, Account> getByKeysForUpdate(Collection<Integer> keys);
}
