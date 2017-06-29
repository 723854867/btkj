package org.btkj.vehicle.mongo;

import org.bson.Document;
import org.btkj.pojo.entity.Renewal;
import org.rapid.data.storage.mapper.MongoMapper;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

public class RenewalMapper extends MongoMapper<String, Renewal> {
	
	private final String FIELD_VIN					= "tips.vin";
	private final String FIELD_LICENSE				= "tips.license";
	
	public RenewalMapper() {
		super("renewal");
	}
	
	@Override
	public void insert(Renewal model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), serial(model), new UpdateOptions().upsert(true));
	}
	
	public Renewal getByVin(String vin) {
		Document document = mongo.findOne(collection, Filters.eq(FIELD_VIN, vin));
		return null == document ? null : deserial(document);
	}
}
