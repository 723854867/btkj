package org.btkj.user.persistence.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.Community;
import org.btkj.user.persistence.provider.CommunitySQLProvider;
import org.rapid.data.storage.db.Dao;

public interface CommunityDao extends Dao<Integer, Community> { 

	@Override
	@SelectProvider(type = CommunitySQLProvider.class, method = "selectByKey")
	Community selectByKey(Integer key);
}
