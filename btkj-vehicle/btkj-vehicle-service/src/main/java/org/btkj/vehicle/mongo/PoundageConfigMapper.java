package org.btkj.vehicle.mongo;

import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.rapid.data.storage.mapper.MongoMapper;
import org.springframework.stereotype.Component;

@Component("poundageConfigMapper")
public class PoundageConfigMapper extends MongoMapper<String, PoundageConfig> {

	public PoundageConfigMapper() {
		super("PoundageConfig");
	}
}
