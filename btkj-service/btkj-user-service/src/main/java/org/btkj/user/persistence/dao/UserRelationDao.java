package org.btkj.user.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.UserRelation;
import org.btkj.user.persistence.provider.TenantUserSQLProvider;
import org.rapid.data.storage.db.Dao;
import org.rapid.util.common.key.Pair;

public interface UserRelationDao extends Dao<Pair<Integer, Integer>, UserRelation> {

	@Override
	@InsertProvider(type = TenantUserSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(UserRelation entity);
	
	@Override
	@SelectProvider(type = TenantUserSQLProvider.class, method = "selectByKey")
	UserRelation selectByKey(Pair<Integer, Integer> key);
	
	@Override
	@SelectProvider(type = TenantUserSQLProvider.class, method = "selectAll")
	List<UserRelation> selectAll();
}
