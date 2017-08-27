package org.btkj.config.mybatis.dao;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.btkj.config.mybatis.provider.PrivilegeSQLProvider;
import org.btkj.config.pojo.entity.Privilege;
import org.rapid.data.storage.mapper.DBMapper;

public interface PrivilegeDao extends DBMapper<String, Privilege> {
	
	@Override
	@InsertProvider(type = PrivilegeSQLProvider.class, method = "replace")
	void replace(Collection<Privilege> collection);

	@MapKey("id")
	@SelectProvider(type = PrivilegeSQLProvider.class, method = "getByTarTypeAndTarId")
	Map<String, Privilege> getByTarTypeAndTarId(@Param("tarType") int tarType, @Param("tarId") int tarId);
	
	@DeleteProvider(type = PrivilegeSQLProvider.class, method = "deleteByTarTypeAndTarId")
	void deleteByTarTypeAndTarId(@Param("tarType") int tarType, @Param("tarId") int tarId);
}
