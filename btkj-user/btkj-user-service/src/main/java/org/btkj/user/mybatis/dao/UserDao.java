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
import org.btkj.pojo.po.UserPO;
import org.btkj.user.mybatis.provider.UserSQLProvider;
import org.btkj.user.pojo.submit.UserSearcher;
import org.rapid.data.storage.mapper.DBMapper;

public interface UserDao extends DBMapper<Integer, UserPO> {
	
	@Override
	@InsertProvider(type = UserSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
	void insert(UserPO entity);
	
	@Override
	@SelectProvider(type = UserSQLProvider.class, method = "getByKey")
	UserPO getByKey(Integer key);

	@SelectProvider(type = UserSQLProvider.class, method = "getByMobile")
	UserPO getByMobile(@Param("appId") int appId, @Param("mobile") String mobile);
	
	@Override
	@MapKey("uid")
	@SelectProvider(type = UserSQLProvider.class, method = "getByKeys")
	Map<Integer, UserPO> getByKeys(Collection<Integer> keys);
	
	@SelectProvider(type = UserSQLProvider.class, method = "count")
	int count(UserSearcher searcher);
	
	@SelectProvider(type = UserSQLProvider.class, method = "paging")
	List<UserPO> paging(UserSearcher searcher);
	
	@Override
	@UpdateProvider(type = UserSQLProvider.class, method = "update")
	void update(UserPO entity);
}
