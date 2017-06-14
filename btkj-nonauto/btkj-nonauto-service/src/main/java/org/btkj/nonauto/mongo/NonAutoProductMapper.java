package org.btkj.nonauto.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.conversions.Bson;
import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.NonAutoProductSearcher;
import org.rapid.data.storage.mapper.MongoMapper;
import org.rapid.data.storage.mongo.KeyMapper;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class NonAutoProductMapper extends MongoMapper<Long, NonAutoProduct> {
	
	private String FIELD_CID				= "cid";
	private String FIELD_TAGS				= "tags";
	private String FIELD_FILTERS			= "filters";
	
	private KeyMapper keyMapper;

	public NonAutoProductMapper() {
		super("NonAutoProduct");
	}
	
	@Override
	public NonAutoProduct insert(NonAutoProduct model) {
		if (0 == model.get_id())
			model.set_id(keyMapper.getAndInc(collection, 1));
		return super.insert(model);
	}
	
	@SuppressWarnings("unchecked")
	public Pager<NonAutoProduct> productList(NonAutoProductSearcher searcher) {
		List<Bson> list = new ArrayList<Bson>();
		Map<String, String> filters = searcher.getFilters();
		if (null != filters) {														// 分类筛选
			List<Bson> l = new ArrayList<Bson>();
			for (Entry<String, String> entry : filters.entrySet())
				l.add(Filters.eq(FIELD_FILTERS + "." + entry.getKey(), entry.getValue()));
			list.add(Filters.and(l));
		}
		List<String> tags = searcher.getTags();
		if (null != tags) 															// 标签筛选
			list.add(Filters.all(FIELD_TAGS, tags));
		if (null != searcher.getCid())
			list.add(Filters.eq(FIELD_CID, searcher.getCid()));
		long total = list.isEmpty() ? mongo.count(collection) : mongo.count(collection, Filters.and(list));
		if (0 == total)
			return Pager.EMPLTY;
		
		searcher.calculate((int) total);
		List<NonAutoProduct> products = null;
		if (null != searcher.getSort())
			products = list.isEmpty() ? 
					mongo.pagingAndSort(collection, searcher.isDesc() ? 
							Sorts.descending(searcher.getSort().statisticField()) : 
								Sorts.ascending(searcher.getSort().statisticField()),  
								searcher.getStart(), searcher.getPageSize(), NonAutoProduct.class):
					mongo.pagingAndSort(collection, Filters.and(list), searcher.isDesc() ? 
							Sorts.descending(searcher.getSort().statisticField()) : 
								Sorts.ascending(searcher.getSort().statisticField()),  
								searcher.getStart(), searcher.getPageSize(), NonAutoProduct.class);
		else
			products = list.isEmpty() ? 
					mongo.paging(collection, searcher.getStart(), searcher.getPageSize(), NonAutoProduct.class):
					mongo.paging(collection, Filters.and(list), searcher.getStart(), searcher.getPageSize(), NonAutoProduct.class);
		return new Pager<NonAutoProduct>(searcher.getTotal(), products);
	}
	
	public void setKeyMapper(KeyMapper keyMapper) {
		this.keyMapper = keyMapper;
	}
}
