package org.btkj.nonauto.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.conversions.Bson;
import org.btkj.nonauto.pojo.param.NonAutoProductListParam;
import org.btkj.pojo.bo.Pager;
import org.btkj.pojo.po.NonAutoProduct;
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
	public void insert(NonAutoProduct model) {
		if (0 == model.get_id())
			model.set_id(keyMapper.getAndInc(collection, 1));
		super.insert(model);
	}
	
	public Pager<NonAutoProduct> products(NonAutoProductListParam param) {
		List<Bson> list = new ArrayList<Bson>();
		Map<String, String> filters = param.getFilters();
		if (null != filters) {														// 分类筛选
			List<Bson> l = new ArrayList<Bson>();
			for (Entry<String, String> entry : filters.entrySet())
				l.add(Filters.eq(FIELD_FILTERS + "." + entry.getKey(), entry.getValue()));
			list.add(Filters.and(l));
		}
		List<String> tags = param.getTags();
		if (null != tags) 															// 标签筛选
			list.add(Filters.all(FIELD_TAGS, tags));
		if (0 != param.getCid())
			list.add(Filters.eq(FIELD_CID, param.getCid()));
		long total = list.isEmpty() ? mongo.count(collection) : mongo.count(collection, Filters.and(list));
		if (0 == total)
			return Pager.EMPLTY;
		
		param.calculate((int) total);
		List<NonAutoProduct> products = null;
		if (null != param.getSort())
			products = list.isEmpty() ? 
					mongo.pagingAndSort(collection, param.isDesc() ? 
							Sorts.descending(param.getSort().statisticField()) : 
								Sorts.ascending(param.getSort().statisticField()),  
								param.getStart(), param.getPageSize(), NonAutoProduct.class):
					mongo.pagingAndSort(collection, Filters.and(list), param.isDesc() ? 
							Sorts.descending(param.getSort().statisticField()) : 
								Sorts.ascending(param.getSort().statisticField()),  
								param.getStart(), param.getPageSize(), NonAutoProduct.class);
		else
			products = list.isEmpty() ? 
					mongo.paging(collection, param.getStart(), param.getPageSize(), NonAutoProduct.class):
					mongo.paging(collection, Filters.and(list), param.getStart(), param.getPageSize(), NonAutoProduct.class);
		return new Pager<NonAutoProduct>(total, products);
	}
	
	public void setKeyMapper(KeyMapper keyMapper) {
		this.keyMapper = keyMapper;
	}
}
