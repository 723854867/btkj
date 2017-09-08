package org.btkj.vehicle.mongo;

import org.btkj.pojo.entity.vehicle.Renewal;
import org.rapid.data.storage.mapper.MongoMapper;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;

@Component("renewalMapper")
public class RenewalMapper extends MongoMapper<String, Renewal> {
	
	private final String FIELD_LICENSE				= "tips.license";
	private final String FIELD_CREATED				= "created";
	
	public RenewalMapper() {
		super("Renewal");
	}
	
	@Override
	public void insert(Renewal model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), model, new UpdateOptions().upsert(true));
	}
	
	public Renewal getByLicense(String license) {
		return mongo.findOne(collection, Filters.eq(FIELD_LICENSE, license), Sorts.descending(FIELD_CREATED), clazz);
	}
}
