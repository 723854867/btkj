package org.btkj.nonauto.mongo;

import java.util.List;

import org.btkj.pojo.po.NonAutoCategory;
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
	public void insert(NonAutoCategory model) {
		if (0 == model.get_id())
			model.set_id(keyMapper.getAndInc(collection, 1));
		super.insert(model);
	}
	
	public NonAutoCategory getByName(String name) {
		return mongo.findOne(collection, Filters.eq(FIELD_NAME, name), clazz);
	}
	
	public List<NonAutoCategory> getByKeys(List<Long> keys) {
		return mongo.find(collection, Filters.in(FIELD_ID, keys), clazz);
	}
	
	public void setKeyMapper(KeyMapper keyMapper) {
		this.keyMapper = keyMapper;
	}
}
