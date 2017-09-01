package org.btkj.vehicle.mongo;

import org.btkj.vehicle.pojo.entity.PoundageConfig;
import org.rapid.data.storage.mapper.MongoMapper;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

@Component("poundageConfigMapper")
public class PoundageConfigMapper extends MongoMapper<String, PoundageConfig> {

	public PoundageConfigMapper() {
		super("PoundageConfig");
	}
	
	@Override
	public void insert(PoundageConfig model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.get_id()), model, new UpdateOptions().upsert(true));
	}
}
