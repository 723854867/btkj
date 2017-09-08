package org.btkj.user.mongo;

import java.util.List;
import java.util.Map;

import org.btkj.pojo.model.BonusScale;
import org.btkj.pojo.model.Pager;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.data.storage.mapper.MongoMapper;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Service("bonusScaleMapper")
public class BonusScaleMapper extends MongoMapper<String, BonusScale> {
	
	private static final String FIELD_TID				= "tid";
	private static final String FIELD_CREATED			= "created";

	public BonusScaleMapper() {
		super("BonusScale");
	}
	
	public void insert(Map<String, BonusScale> entities) {
		mongo.bulkReplaceOne(collection, entities);
	}
	
	public Pager<BonusScale> paging(int tid, EmployeeParam param) {
		long total = mongo.count(collection, Filters.eq(FIELD_ID, tid));
		if (0 == total)
			return Pager.EMPLTY;
		param.calculate((int) total);
		List<BonusScale> list = mongo.pagingAndSort(collection, Filters.eq(FIELD_ID, tid), Sorts.descending(FIELD_CREATED), param.getStart(), param.getPageSize(), BonusScale.class);
		return new Pager<BonusScale>(total, list);
	}
}
