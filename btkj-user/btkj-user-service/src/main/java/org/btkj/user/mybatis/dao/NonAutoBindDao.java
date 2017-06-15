package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.NonAutoBind;
import org.btkj.user.mybatis.provider.NonAutoBindSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface NonAutoBindDao extends DBMapper<Integer, NonAutoBind> {

	@SelectProvider(type = NonAutoBindSQLProvider.class, method = "getByTid")
	List<NonAutoBind> getByTid(int tid);
}
