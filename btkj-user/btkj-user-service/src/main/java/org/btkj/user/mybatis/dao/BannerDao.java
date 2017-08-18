package org.btkj.user.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.mybatis.provider.BannerSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface BannerDao extends DBMapper<Integer, Banner> {
	
	@Override
	@InsertProvider(type = BannerSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(Banner model);
	
	@Override
	@SelectProvider(type = BannerSQLProvider.class, method = "getByKey")
	Banner getByKey(Integer key);
	
	@MapKey("id")
	@SelectProvider(type = BannerSQLProvider.class, method = "getByAppIdAndTid")
	Map<Integer, Banner> getByAppIdAndTid(@Param("appId") int appId, @Param("tid") int tid);
	
	@Override
	@UpdateProvider(type = BannerSQLProvider.class, method = "update")
	void update(Banner model);
	
	@Override
	@DeleteProvider(type = BannerSQLProvider.class, method = "delete")
	void delete(Integer key);
}
