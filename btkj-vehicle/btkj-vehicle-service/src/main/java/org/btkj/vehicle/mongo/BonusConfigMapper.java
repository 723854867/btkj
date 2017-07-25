package org.btkj.vehicle.mongo;

import org.btkj.pojo.po.BonusConfig;
import org.rapid.data.storage.mapper.MongoMapper;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

@Component("bonusConfigMapper")
public class BonusConfigMapper extends MongoMapper<String, BonusConfig> {

	public BonusConfigMapper() {
		super("BonusConfig");
	}
	
	@Override
	public void insert(BonusConfig model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), model, new UpdateOptions().upsert(true));
	}
}
