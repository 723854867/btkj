package org.btkj.user.persistence.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.btkj.pojo.entity.SpecialCommission;
import org.btkj.user.persistence.provider.SpecialCommissionSQLProvider;
import org.rapid.data.storage.db.Dao;

public interface SpecialCommissionDao extends Dao<Integer, SpecialCommission> {

	@Override
	@InsertProvider(type = SpecialCommissionSQLProvider.class, method = "insert")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insert(SpecialCommission specialCommission);
	
	@SelectProvider(type = SpecialCommissionSQLProvider.class, method = "selectByeid")
	SpecialCommission selectByeid(int eid);
	
	/**
	 * 商家管理后台对雇员特殊佣金设置修改
	 * @param entity
	 */
	@UpdateProvider(type = SpecialCommissionSQLProvider.class, method = "updateInfo")
	void updateInfo(SpecialCommission specialCommission);
}
