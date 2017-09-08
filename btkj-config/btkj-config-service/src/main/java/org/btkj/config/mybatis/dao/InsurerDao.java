package org.btkj.config.mybatis.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.config.mybatis.provider.InsurerSQLProvider;
import org.btkj.pojo.entity.config.Insurer;
import org.rapid.data.storage.mapper.DBMapper;

public interface InsurerDao extends DBMapper<Integer, Insurer> {
	
	@Override
	@InsertProvider(type = InsurerSQLProvider.class, method = "insert")
	void insert(Insurer model);

	@Override
	@SelectProvider(type = InsurerSQLProvider.class, method = "getByKey")
	Insurer getByKey(Integer key);
	
	@Override
	@MapKey("id")
	@SelectProvider(type = InsurerSQLProvider.class, method = "getAll")
	Map<Integer, Insurer> getAll();
	
	@Override
	@MapKey("id")
	@SelectProvider(type = InsurerSQLProvider.class, method = "getByKeys")
	Map<Integer, Insurer> getByKeys(Collection<Integer> keys);
	
	@Override
	@UpdateProvider(type = InsurerSQLProvider.class, method = "update")
	void update(Insurer model);
}
