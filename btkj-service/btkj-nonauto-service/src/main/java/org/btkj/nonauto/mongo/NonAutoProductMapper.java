package org.btkj.nonauto.mongo;

import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.NonAutoProductSearcher;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.data.storage.mongo.KeyMapper;

import com.mongodb.client.model.Filters;

public class NonAutoProductMapper extends MongoMapper<Long, NonAutoProduct> {
	
	private String FIELD_FILTERS			= "filters";
	
	private KeyMapper keyMapper;

	public NonAutoProductMapper() {
		super("NonAutoProduct");
	}
	
	@Override
	public NonAutoProduct insert(NonAutoProduct model) {
		if (0 == model.get_id())
			model.set_id(keyMapper.getAndInc(collection));
		return super.insert(model);
	}
	
	@Override
	public void update(NonAutoProduct model) {
		mongo.replace(collection, Filters.eq(FIELD_ID, model.get_id()), serial(model));
	}
	
	public Pager<NonAutoProduct> productList(NonAutoProductSearcher searcher) { 
//		long count = mongo.count(collection, Filters.elemMatch(FIELD_FILTERS, filter));
		return null;
	}
	
	public void setKeyMapper(KeyMapper keyMapper) {
		this.keyMapper = keyMapper;
	}
}
