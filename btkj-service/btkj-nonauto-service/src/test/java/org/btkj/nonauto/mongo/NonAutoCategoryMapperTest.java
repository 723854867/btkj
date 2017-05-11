package org.btkj.nonauto.mongo;

import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.entity.NonAutoCategory;
import org.btkj.pojo.entity.NonAutoCategory.Filter;
import org.rapid.data.storage.mongo.KeyMapper;
import org.rapid.data.storage.mongo.Mongo;
import org.rapid.util.common.serializer.SerializeUtil;

public class NonAutoCategoryMapperTest {

	public static void main(String[] args) {
		Mongo mongo = new Mongo();
		mongo.setDb("btkj-test");
		mongo.setHost("101.37.30.26");
		KeyMapper keyMapper = new KeyMapper();
		keyMapper.setMongo(mongo);
		NonAutoCategoryMapper mapper = new NonAutoCategoryMapper();
		mapper.setMongo(mongo);
		mapper.setKeyMapper(keyMapper);
//		NonAutoCategory category = new NonAutoCategory();
//		category.setName("寿险");
//		List<String> list = new ArrayList<String>();
//		list.add("返利");
//		list.add("销量");
//		list.add("价格");
//		category.setSorts(list);
//		List<Filter> filters = new ArrayList<Filter>();
//		Filter filter = new Filter();
//		filter.setName("时间");
//		list = new ArrayList<String>();
//		list.add("2016");
//		list.add("2017");
//		list.add("2018");
//		filter.setOptions(list);
//		filters.add(filter);
//		
//		filter = new Filter();
//		filter.setName("年龄");
//		list = new ArrayList<String>();
//		list.add("10");
//		list.add("20");
//		list.add("30");
//		filter.setOptions(list);
//		filters.add(filter);
//		
//		filter = new Filter();
//		filter.setName("保险公司");
//		list = new ArrayList<String>();
//		list.add("平安保险");
//		list.add("人寿保险");
//		list.add("天安保险");
//		filter.setOptions(list);
//		filters.add(filter);
//		category.setFilters(filters);
//		
//		list = new ArrayList<String>();
//		list.add("热门");
//		list.add("vi");
//		list.add("一对");
//		category.setTages(list);
//		category.set_id(1);
//		mapper.update(category);
		NonAutoCategory category = mapper.getByName("寿险");
		System.out.println(SerializeUtil.JsonUtil.GSON.toJson(category));
	}
}
