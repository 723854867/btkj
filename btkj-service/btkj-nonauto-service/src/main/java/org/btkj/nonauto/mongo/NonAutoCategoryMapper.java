package org.btkj.nonauto.mongo;

import org.bson.Document;
import org.btkj.pojo.entity.NonAutoCategory;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.data.storage.mongo.KeyMapper;

import com.mongodb.client.model.Filters;

public class NonAutoCategoryMapper extends MongoMapper<Long, NonAutoCategory> {
	
	private String FIELD_NAME 							= "name";
	
	private KeyMapper keyMapper;
	
	public NonAutoCategoryMapper() {
		super("NonAutoCategory");
	}
	
	@Override
	public NonAutoCategory insert(NonAutoCategory model) {
		if (0 == model.get_id())
			model.set_id(keyMapper.getAndInc(collection));
		return super.insert(model);
	}
	
	public NonAutoCategory getByName(String name) {
		Document document = mongo.findOne(collection, Filters.eq(FIELD_NAME, name));
		return null == document ? null : deserial(document);
	}
	
	@Override
	public void update(NonAutoCategory model) {
		mongo.replace(collection, Filters.eq(FIELD_ID, model.get_id()), serial(model));
	}
	
	public void setKeyMapper(KeyMapper keyMapper) {
		this.keyMapper = keyMapper;
	}
}
