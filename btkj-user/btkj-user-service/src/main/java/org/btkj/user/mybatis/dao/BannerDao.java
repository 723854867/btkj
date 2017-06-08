package org.btkj.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.mybatis.provider.BannerSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface BannerDao extends Dao<Integer, Banner> {
	
	/**
	 * 通过 appId 和  tid 获取 banner
	 * 
	 * @param appId
	 * @param tid
	 * @return
	 */
	@SelectProvider(type = BannerSQLProvider.class, method = "selectByAppIdAndTid")
	List<Banner> selectByAppIdAndTid(@Param("appId") int appId, @Param("tid") int tid);
}
