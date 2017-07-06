package org.btkj.payment.redis;

import org.btkj.payment.mybatis.dao.AccountDao;
import org.btkj.payment.pojo.entity.Account;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class AccountMapper extends RedisDBAdapter<Integer, Account, AccountDao> {

	public AccountMapper() {
		super(new ByteProtostuffSerializer<Account>(), "hash:db:account");
	}
}
