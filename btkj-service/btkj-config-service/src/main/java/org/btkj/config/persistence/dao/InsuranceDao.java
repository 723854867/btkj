package org.btkj.config.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.InsuranceSQLProvider;
import org.btkj.pojo.entity.Insurance;
import org.rapid.data.storage.db.Dao;

public interface InsuranceDao extends Dao<Integer, Insurance> {
	
	@Override
	@InsertProvider(type = InsuranceSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Insurance entity);

	@Override
	@SelectProvider(type = InsuranceSQLProvider.class, method = "selectAll")
	List<Insurance> selectAll();
	
	@Override
	@SelectProvider(type = InsuranceSQLProvider.class, method = "selectByKey")
	Insurance selectByKey(Integer key);
}
