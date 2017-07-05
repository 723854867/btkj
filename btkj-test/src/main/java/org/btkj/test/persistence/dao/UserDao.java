package org.btkj.test.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.User;
import org.btkj.test.persistence.mapper.UserSQLProvider;
import org.btkj.user.pojo.submit.TenantSearcher;

public interface UserDao {

	@SelectProvider(type = UserSQLProvider.class, method = "selectForUpdate")
	List<User> selectForUpdate(int appId);
	
	@SelectProvider(type = UserSQLProvider.class, method = "selectByAppId")
	List<User> selectByAppId(int appId);
	
	@SelectProvider(type = UserSQLProvider.class, method = "selectByUid")
	User selectByUid(int uid);
	
	@InsertProvider(type = UserSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
	void insert(User user);
	
	@SelectProvider(type = UserSQLProvider.class, method = "count")
	int count(TenantSearcher searcher);
}
