package org.btkj.user.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.NonAutoInsurance;
import org.btkj.user.persistence.provider.NonAutoInsuranceSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface NonAutoInsuranceDao extends Dao<Integer, NonAutoInsurance> {

	@SelectProvider(type = NonAutoInsuranceSQLProvider.class, method = "selectByAppIdAndTid")
	List<NonAutoInsurance> selectByAppIdAndTid(@Param("appId") int appId, @Param("tid") int tid);
}
