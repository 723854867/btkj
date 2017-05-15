package org.btkj.user.persistence.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.SpecialBonus;
import org.btkj.user.persistence.provider.SpecialBonusSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface SpecialBonusDao extends Dao<Integer, SpecialBonus> {

	@Override
	@InsertProvider(type = SpecialBonusSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(SpecialBonus specialBonus);
	
	@SelectProvider(type = SpecialBonusSQLProvider.class, method = "selectByeid")
	SpecialBonus selectByeid(int eid);
	
	/**
	 * 商家管理后台对雇员特殊佣金设置修改
	 * @param entity
	 */
	@UpdateProvider(type = SpecialBonusSQLProvider.class, method = "update")
	void update(SpecialBonus specialBonus);
}
