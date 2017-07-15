package org.btkj.test.persistence.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.User;
import org.btkj.test.persistence.mapper.UserSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface UserDao extends DBMapper<Integer, User> {
	
	@Override
	@InsertProvider(type = UserSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
	void insert(User model);

	@Override
	@MapKey(value = "uid")
	@SelectProvider(type = UserSQLProvider.class, method = "getAll")
	Map<Integer, User> getAll();
	
	@Override
	@SelectProvider(type = UserSQLProvider.class, method = "getByKey")
	User getByKey(Integer key);
	
	@Override
	@MapKey(value = "uid")
	@SelectProvider(type = UserSQLProvider.class, method = "getByKeys")
	Map<Integer, User> getByKeys(Collection<Integer> keys);
	
	@Override
	@UpdateProvider(type = UserSQLProvider.class, method = "update")
	void update(User model);
}
