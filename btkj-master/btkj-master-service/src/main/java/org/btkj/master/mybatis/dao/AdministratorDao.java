package org.btkj.master.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.master.mybatis.provider.AdministratorSQLProvider;
import org.btkj.pojo.entity.Administrator;
import org.rapid.data.storage.mapper.DBMapper;

public interface AdministratorDao extends DBMapper<Integer, Administrator> {

	@Override
	@SelectProvider(type = AdministratorSQLProvider.class, method = "getByKey")
	Administrator getByKey(Integer id);
}
