package org.btkj.user.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.App;
import org.btkj.user.persistence.provider.AppSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface AppDao extends Dao<Integer, App> {
	
	@Override
	@InsertProvider(type = AppSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(App entity);

	@Override
	@SelectProvider(type = AppSQLProvider.class, method = "selectAll")
	List<App> selectAll();
	
	@Override
	@SelectProvider(type = AppSQLProvider.class, method = "selectByKey")
	App selectByKey(Integer key);
}
