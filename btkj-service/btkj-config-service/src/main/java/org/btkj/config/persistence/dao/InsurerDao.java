package org.btkj.config.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.InsuranceSQLProvider;
import org.btkj.pojo.entity.Insurer;
import org.rapid.data.storage.db.Dao;

public interface InsurerDao extends Dao<Integer, Insurer> {
	
	@Override
	@InsertProvider(type = InsuranceSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Insurer entity);

	@Override
	@SelectProvider(type = InsuranceSQLProvider.class, method = "selectAll")
	List<Insurer> selectAll();
	
	@Override
	@SelectProvider(type = InsuranceSQLProvider.class, method = "selectByKey")
	Insurer selectByKey(Integer key);
}
