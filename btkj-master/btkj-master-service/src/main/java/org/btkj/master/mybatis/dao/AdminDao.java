package org.btkj.master.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.master.mybatis.provider.AdminSQLProvider;
import org.btkj.pojo.entity.master.Admin;
import org.rapid.data.storage.mapper.DBMapper;

public interface AdminDao extends DBMapper<Integer, Admin> {
	
	@Override
	@InsertProvider(type = AdminSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Admin model);

	@Override
	@SelectProvider(type = AdminSQLProvider.class, method = "getByKey")
	Admin getByKey(Integer key);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = AdminSQLProvider.class, method = "getAll")
	Map<Integer, Admin> getAll();
	
	@Override
	@UpdateProvider(type = AdminSQLProvider.class, method = "update")
	void update(Admin model);
}
