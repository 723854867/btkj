package org.btkj.config.redis;

import java.util.Map;

import org.btkj.config.mybatis.dao.InsurerDao;
import org.btkj.pojo.po.Insurer;
import org.rapid.data.storage.mapper.RedisDBAdapter;
import org.rapid.util.common.serializer.impl.ByteProtostuffSerializer;

public class InsurerMapper extends RedisDBAdapter<Integer, Insurer, InsurerDao> {
	
	public InsurerMapper() {
		super(new ByteProtostuffSerializer<Insurer>(), "hash:db:insurer");
	}
	
	public Insurer getByJianJieId(int jianJieId) {
		Map<Integer, Insurer> insurers = getAll();
		for (Insurer insurer : insurers.values()) {
			if (insurer.getJianJieId() != jianJieId)
				continue;
			return insurer;
		}
		return null;
	}
}
