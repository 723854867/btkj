package org.btkj.user.redis;

import java.util.List;

import org.btkj.pojo.entity.user.Customer;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.user.CustomerListParam;
import org.btkj.user.mybatis.dao.CustomerDao;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class CustomerMapper extends RedisDBAdapter<Long, Customer, CustomerDao> {

	public CustomerMapper() {
		super(new ByteProtostuffSerializer<Customer>(), "hash:db:customer");
	}
	
	public Pager<Customer> paging(CustomerListParam param) { 
		int total = dao.total(param);
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate(total);
		List<Customer> list = dao.paging(param);
		return new Pager<Customer>(total, list);
	}
}
