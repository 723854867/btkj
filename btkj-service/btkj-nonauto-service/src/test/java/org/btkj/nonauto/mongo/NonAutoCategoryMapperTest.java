package org.btkj.nonauto.mongo;

import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.entity.NonAutoCategory;
import org.rapid.data.storage.mongo.KeyMapper;
import org.rapid.data.storage.mongo.Mongo;
import org.rapid.util.common.serializer.SerializeUtil;

public class NonAutoCategoryMapperTest {

	public static void main(String[] args) {
		Mongo mongo = new Mongo();
		mongo.setDb("btkj-test");
		mongo.setHost("101.37.30.26");
		mongo.init();
		KeyMapper keyMapper = new KeyMapper();
		keyMapper.setMongo(mongo);
		NonAutoCategoryMapper mapper = new NonAutoCategoryMapper();
		mapper.setMongo(mongo);
		mapper.setKeyMapper(keyMapper);
		
		List<Long> list = new ArrayList<>();
		list.add(1l);
		list.add(2l);
		list.add(3l);
		List<NonAutoCategory> categories = mapper.getByKeys(list);
		for (NonAutoCategory category : categories)
			System.out.println(SerializeUtil.JsonUtil.GSON.toJson(category));
	}
}
