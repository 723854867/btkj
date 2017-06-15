package org.btkj.config.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.mybatis.provider.InsurerSQLProvider;
import org.btkj.pojo.entity.Insurer;
import org.rapid.data.storage.mapper.DBMapper;

public interface InsurerDao extends DBMapper<Integer, Insurer> {
	
	@Override
	@SelectProvider(type = InsurerSQLProvider.class, method = "getByKey")
	Insurer getByKey(Integer key);
	
	@Override
	@SelectProvider(type = InsurerSQLProvider.class, method = "getWithinKey")
	List<Insurer> getWithinKey(List<Integer> keys);
}
