package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.mybatis.provider.BannerSQLProvider;
import org.rapid.data.storage.mapper.DBMapper;

public interface BannerDao extends DBMapper<Integer, Banner> {
	
	@Override
	@SelectProvider(type = BannerSQLProvider.class, method = "getByKey")
	Banner getByKey(Integer key);
	
	/**
	 * 通过 appId 和  tid 获取 banner
	 * 
	 * @param appId
	 * @param tid
	 * @return
	 */
	@SelectProvider(type = BannerSQLProvider.class, method = "getByAppIdAndTid")
	List<Banner> getByAppIdAndTid(@Param("appId") int appId, @Param("tid") int tid);
	
	@Override
	@UpdateProvider(type = BannerSQLProvider.class, method = "update")
	void update(Banner model);
	
	@Override
	@DeleteProvider(type = BannerSQLProvider.class, method = "delete")
	void delete(Integer key);
}
