package org.btkj.config.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.InsurerSQLProvider;
import org.btkj.pojo.entity.Insurer;
import org.rapid.data.storage.db.Dao;

public interface InsurerDao extends Dao<Integer, Insurer> {
	
	@Override
	@SelectProvider(type = InsurerSQLProvider.class, method = "selectWithinKey")
	List<Insurer> selectWithinKey(List<Integer> keys);
}
