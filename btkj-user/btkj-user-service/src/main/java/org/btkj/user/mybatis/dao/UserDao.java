package org.btkj.user.mybatis.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.User;
import org.btkj.user.mybatis.provider.UserSQLProvider;
import org.btkj.user.pojo.submit.UserSearcher;
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
	@MapKey("uid")
	@SelectProvider(type = UserSQLProvider.class, method = "getByKeys")
	Map<Integer, User> getByKeys(@Param("keys") Collection<Integer> keys);
	
	@SelectProvider(type = UserSQLProvider.class, method = "count")
	int count(UserSearcher searcher);
	
	@SelectProvider(type = UserSQLProvider.class, method = "paging")
	List<User> paging(UserSearcher searcher);
}
