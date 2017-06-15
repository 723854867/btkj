package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.User;
import org.btkj.user.mybatis.provider.UserSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface UserDao extends DBMapper<Integer, User> {
	
	@Override
	@InsertProvider(type = UserSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
	void insert(User entity);
	
	@Override
	@UpdateProvider(type = UserSQLProvider.class, method = "update")
	void update(User entity);
	
	@Override
	@SelectProvider(type = UserSQLProvider.class, method = "getByKey")
	User getByKey(Integer key);

	@SelectProvider(type = UserSQLProvider.class, method = "getByMobile")
	User getByMobile(@Param("appId") int appId, @Param("mobile") String mobile);
	
	@Override
	@SelectProvider(type = UserSQLProvider.class, method = "getWithinKey")
	List<User> getWithinKey(@Param("list") List<Integer> keys);
}
