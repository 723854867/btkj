package org.btkj.master.persistence.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.master.persistence.provider.AdministratorSQLProvider;
import org.btkj.pojo.entity.Administrator;
import org.rapid.data.storage.db.Dao;

public interface AdministratorDao extends Dao<Integer, Administrator> {

	@Override
	@SelectProvider(type = AdministratorSQLProvider.class, method = "selectByKey")
	Administrator selectByKey(Integer id);
}
