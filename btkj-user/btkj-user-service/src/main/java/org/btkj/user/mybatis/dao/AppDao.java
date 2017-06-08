package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.App;
import org.btkj.pojo.info.AppListInfo;
import org.btkj.pojo.submit.AppSearcher;
import org.btkj.user.mybatis.provider.AppSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface AppDao extends Dao<Integer, App> {
	
	@Override
	@InsertProvider(type = AppSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(App entity);
	
	@Override
	@UpdateProvider(type = AppSQLProvider.class, method = "update")
	void update(App entity);

	@Override
	@SelectProvider(type = AppSQLProvider.class, method = "selectAll")
	List<App> selectAll();
	
	@Override
	@SelectProvider(type = AppSQLProvider.class, method = "selectByKey")
	App selectByKey(Integer key);
	
	@SelectProvider(type = AppSQLProvider.class, method = "selectByKeyForUpdate")
	App selectByKeyForUpdate(Integer id);
	
	int searchCount(AppSearcher searcher);
	
	List<AppListInfo> search(AppSearcher searcher);
}
