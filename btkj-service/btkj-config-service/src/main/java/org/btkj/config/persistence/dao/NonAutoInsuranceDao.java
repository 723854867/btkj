package org.btkj.config.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.persistence.provider.NonAutoInsuranceSQLProvider;
import org.btkj.pojo.entity.NonAutoInsurance;
import org.rapid.data.storage.db.Dao;

public interface NonAutoInsuranceDao extends Dao<Integer, NonAutoInsurance> {

	@Override
	@SelectProvider(type = NonAutoInsuranceSQLProvider.class, method = "selectAll")
	List<NonAutoInsurance> selectAll();
	
	@Override
	@SelectProvider(type = NonAutoInsuranceSQLProvider.class, method = "selectByKey")
	NonAutoInsurance selectByKey(Integer key);
}
