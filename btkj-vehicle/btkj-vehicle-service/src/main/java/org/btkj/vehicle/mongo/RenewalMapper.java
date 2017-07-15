package org.btkj.vehicle.mongo;

import org.btkj.pojo.entity.Renewal;
import org.rapid.data.storage.mapper.MongoMapper;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;

public class RenewalMapper extends MongoMapper<String, Renewal> {
	
	private final String FIELD_LICENSE				= "tips.license";
	private final String FIELD_CREATED				= "created";
	
	public RenewalMapper() {
		super("renewal");
	}
	
	@Override
	public void insert(Renewal model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), model, new UpdateOptions().upsert(true));
	}
	
	public Renewal getByLicense(String license) {
		return mongo.findOne(collection, Filters.eq(FIELD_LICENSE, license), Sorts.descending(FIELD_CREATED), clazz);
	}
}
