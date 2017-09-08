package org.btkj.config.mybatis.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.mybatis.provider.PrivilegeSQLProvider;
import org.btkj.pojo.entity.config.Privilege;
import org.rapid.data.storage.mapper.DBMapper;

public interface PrivilegeDao extends DBMapper<String, Privilege> {
	
	@Override
	@InsertProvider(type = PrivilegeSQLProvider.class, method = "replace")
	void replace(Collection<Privilege> collection);

	@MapKey("id")
	@SelectProvider(type = PrivilegeSQLProvider.class, method = "getByTypeAndTarId")
	Map<String, Privilege> getByTypeAndTarId(@Param("type") int type, @Param("tarId") int tarId);
	
	@DeleteProvider(type = PrivilegeSQLProvider.class, method = "deleteByTypeAndTarId")
	void deleteByTypeAndTarId(@Param("type") int type, @Param("tarId") int tarId);
}
