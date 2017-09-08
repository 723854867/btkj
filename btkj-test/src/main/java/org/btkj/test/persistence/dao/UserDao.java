package org.btkj.test.persistence.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.test.persistence.mapper.UserSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface UserDao extends DBMapper<Integer, UserPO> {
	
	@Override
	@InsertProvider(type = UserSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
	void insert(UserPO model);

	@Override
	@MapKey(value = "uid")
	@SelectProvider(type = UserSQLProvider.class, method = "getAll")
	Map<Integer, UserPO> getAll();
	
	@Override
	@SelectProvider(type = UserSQLProvider.class, method = "getByKey")
	UserPO getByKey(Integer key);
	
	@Override
	@MapKey(value = "uid")
	@SelectProvider(type = UserSQLProvider.class, method = "getByKeys")
	Map<Integer, UserPO> getByKeys(Collection<Integer> keys);
	
	@Override
	@UpdateProvider(type = UserSQLProvider.class, method = "update")
	void update(UserPO model);
	
	@InsertProvider(type = UserSQLProvider.class, method = "batchInsert")
	@Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
	void batchInsert(List<UserPO> user);
}
