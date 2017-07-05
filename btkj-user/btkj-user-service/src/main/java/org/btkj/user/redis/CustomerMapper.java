package org.btkj.user.redis;

import org.btkj.pojo.entity.Customer;
import org.btkj.user.mybatis.dao.CustomerDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class CustomerMapper extends RedisDBAdapter<Long, Customer, CustomerDao> {

	public CustomerMapper() {
		super(new ByteProtostuffSerializer<Customer>(), "hash:db:customer");
	}
}
