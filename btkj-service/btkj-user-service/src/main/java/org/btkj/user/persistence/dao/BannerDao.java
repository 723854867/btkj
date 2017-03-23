package org.btkj.user.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.pojo.entity.Banner;
import org.btkj.user.persistence.provider.BannerSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface BannerDao extends Dao<Integer, Banner> {

	@Override
	@SelectProvider(type = BannerSQLProvider.class, method = "selectAll")
	List<Banner> selectAll();
}
