package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.NonAutoBind;
import org.btkj.user.mybatis.provider.NonAutoBindSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface NonAutoBindDao extends Dao<Integer, NonAutoBind> {

	@SelectProvider(type = NonAutoBindSQLProvider.class, method = "selectByTid")
	List<NonAutoBind> selectByTid(int tid);
}
