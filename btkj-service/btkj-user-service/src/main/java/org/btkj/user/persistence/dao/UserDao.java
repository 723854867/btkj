package org.btkj.user.persistence.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.User;
import org.btkj.user.persistence.provider.UserSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface UserDao extends Dao<Integer, User> {
	
	@Override
	@InsertProvider(type = UserSQLProvider.class, method = "insert")
	void insert(User entity);
	
	@Override
	@SelectProvider(type = UserSQLProvider.class, method = "selectByKey")
	User selectByKey(Integer key);

	@SelectProvider(type = UserSQLProvider.class, method = "getByAppIdAndMobile")
	User getByAppIdAndMobile(@Param("appId") int appId, @Param("mobile") String mobile);
}
