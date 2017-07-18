package org.btkj.master.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.master.mybatis.provider.AdministratorSQLProvider;
import org.btkj.pojo.po.Administrator;
import org.rapid.data.storage.mapper.DBMapper;

public interface AdministratorDao extends DBMapper<Integer, Administrator> {
	
	@Override
	@InsertProvider(type = AdministratorSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Administrator model);

	@Override
	@SelectProvider(type = AdministratorSQLProvider.class, method = "getByKey")
	Administrator getByKey(Integer key);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = AdministratorSQLProvider.class, method = "getAll")
	Map<Integer, Administrator> getAll();
}
