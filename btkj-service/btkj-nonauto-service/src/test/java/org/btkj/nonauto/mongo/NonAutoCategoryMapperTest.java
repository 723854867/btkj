package org.btkj.nonauto.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.NonAutoProduct;
import org.btkj.pojo.enums.SortField;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.submit.NonAutoProductSearcher;
import org.rapid.data.storage.mongo.KeyMapper;
import org.rapid.data.storage.mongo.Mongo;

public class NonAutoCategoryMapperTest {

	public static void main(String[] args) {
		Mongo mongo = new Mongo();
		mongo.setDb("btkj-test");
		mongo.setHost("101.37.30.26");
		mongo.init();
		KeyMapper keyMapper = new KeyMapper();
		keyMapper.setMongo(mongo);
		NonAutoProductMapper mapper = new NonAutoProductMapper();
		mapper.setKeyMapper(keyMapper);
		mapper.setMongo(mongo);
		
		NonAutoProductSearcher searcher = new NonAutoProductSearcher();
		searcher.setCid(1);
		searcher.setPage(2);
		searcher.setPageSize(10);
		Map<String, String> map = new HashMap<String, String>();
		map.put("时间", "2014");
		map.put("保险公司", "人寿保险");
		searcher.setFilters(map);
		List<String> tags = new ArrayList<>();
		tags.add("推荐");
		tags.add("热门");
		searcher.setTags(tags);
		searcher.setSort(SortField.PRICE);
		Pager<NonAutoProduct> products = mapper.productList(searcher);
		for (NonAutoProduct product : products.getList())
			System.out.println(product.get_id() + " " + product.getCreated() + " " + product.getPrice());
		
//		List<NonAutoProduct> list = mongo.find("NonAutoProduct",
//				Filters.and(Filters.eq("filters.时间", "2017"), Filters.eq("filters.保险公司", "人寿保险")), NonAutoProduct.class);
//		for (NonAutoProduct product : list)
//			System.out.println(product.get_id());
		
//		NonAutoProduct product = new NonAutoProduct();
//		product.setCid(1);
//		product.setName("好产品");
//		product.setPrice("500.23");
//		product.setRebate("20.25");
//		product.setInsurerId(1);
//		product.setInsurerName("太平");
//		product.setSales(0);
//		product.setCoverage("100万");
//		product.setIcon("sss");
//		product.setLink("sss");
//		List<String> tags = new ArrayList<String>();
//		tags.add("热门");
//		tags.add("推荐");
//		product.setTags(tags);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("时间", "2014");
//		map.put("保险公司", "人寿");
//		product.setFilters(map);
//		tags = new ArrayList<String>();
//		tags.add("疾病		10万元");
//		tags.add("意外		0元");
//		tags.add("xx		12万元");
//		product.setLiabilities(tags);
//		product.setCreated(DateUtils.currentTime());
//		product.setUpdated(DateUtils.currentTime());
//		mapper.insert(product);
	}
}
