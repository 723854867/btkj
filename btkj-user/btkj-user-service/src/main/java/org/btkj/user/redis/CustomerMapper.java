package org.btkj.user.redis;

import java.util.List;

import org.btkj.pojo.entity.Customer;
import org.btkj.pojo.model.Pager;
import org.btkj.user.mybatis.dao.CustomerDao;
import org.btkj.user.pojo.submit.CustomerSearcher;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class CustomerMapper extends RedisDBAdapter<Long, Customer, CustomerDao> {

	public CustomerMapper() {
		super(new ByteProtostuffSerializer<Customer>(), "hash:db:customer");
	}
	
	public Pager<Customer> paging(CustomerSearcher searcher) { 
		int total = dao.total(searcher);
		if (0 == total)
			return Pager.EMPLTY;
		searcher.calculate(total);
		List<Customer> list = dao.paging(searcher);
		return new Pager<Customer>(searcher.getTotal(), list);
	}
}
