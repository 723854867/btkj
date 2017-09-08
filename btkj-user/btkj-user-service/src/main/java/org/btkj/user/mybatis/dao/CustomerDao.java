package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.user.Customer;
import org.btkj.user.mybatis.provider.CustomerSQLProvider;
import org.btkj.user.pojo.submit.CustomerSearcher;
import org.rapid.data.storage.mapper.DBMapper;

public interface CustomerDao extends DBMapper<Long, Customer> {

	@Override
	@SelectProvider(type = CustomerSQLProvider.class, method = "getByKey")
	Customer getByKey(Long key);
	
	@Override
	@InsertProvider(type = CustomerSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Customer model);
	
	@SelectProvider(type = CustomerSQLProvider.class, method = "total")
	int total(CustomerSearcher searcher);
	
	@SelectProvider(type = CustomerSQLProvider.class, method = "paging")
	List<Customer> paging(CustomerSearcher searcher);
	
	@Override
	@UpdateProvider(type = CustomerSQLProvider.class, method = "update")
	void update(Customer model);
	
	@Override
	@DeleteProvider(type = CustomerSQLProvider.class, method = "delete")
	void delete(Long key);
}
