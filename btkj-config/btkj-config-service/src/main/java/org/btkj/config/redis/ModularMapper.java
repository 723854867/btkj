package org.btkj.config.redis;

import org.btkj.config.mybatis.dao.ModularDao;
import org.btkj.config.pojo.entity.Modular;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

/**
 * 该缓存仅仅缓存 name，mod 值，不做结构缓存，因为 left，right等值的修改是直接操作 db 的
 * 
 * @author ahab
 *
 */
public class ModularMapper extends RedisDBAdapter<Integer, Modular, ModularDao> {

	public ModularMapper() {
		super(new ByteProtostuffSerializer<Modular>(), "hash:db:modular");
	}
}
