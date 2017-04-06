package org.btkj.master.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.master.persistence.domain.Administrator;
import org.btkj.master.persistence.provider.AdministratorSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface AdministratorDao extends Dao<Integer, Administrator> {

	@Override
	@SelectProvider(type = AdministratorSQLProvider.class, method = "selectAll")
	List<Administrator> selectAll();
	
	@Override
	@UpdateProvider(type = AdministratorSQLProvider.class, method = "update")
	void update(Administrator entity);
}
