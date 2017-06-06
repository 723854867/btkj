package org.btkj.user.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.UserListInfo;
import org.btkj.pojo.submit.UserSearcher;
import org.btkj.user.persistence.provider.UserSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface UserDao extends Dao<Integer, User> {
	
	@Override
	@InsertProvider(type = UserSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
	void insert(User entity);
	
	@Override
	@UpdateProvider(type = UserSQLProvider.class, method = "update")
	void update(User entity);
	
	@Override
	@SelectProvider(type = UserSQLProvider.class, method = "selectByKey")
	User selectByKey(Integer key);

	@SelectProvider(type = UserSQLProvider.class, method = "selectByMobile")
	User selectByMobile(@Param("appId") int appId, @Param("mobile") String mobile);
	
	@Override
	@SelectProvider(type = UserSQLProvider.class, method = "selectWithinKey")
	List<User> selectWithinKey(@Param("list") List<Integer> keys);
	
	int searchCount(UserSearcher searcher);
	
	List<UserListInfo> search(UserSearcher searcher);
}
