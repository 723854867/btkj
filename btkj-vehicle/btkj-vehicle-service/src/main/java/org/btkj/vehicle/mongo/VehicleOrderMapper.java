package org.btkj.vehicle.mongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.conversions.Bson;
import org.btkj.pojo.entity.VehicleOrder;
import org.btkj.pojo.model.EmployeeForm;
import org.btkj.vehicle.pojo.model.VehicleOrderSearcher;
import org.rapid.data.storage.mapper.MongoMapper;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;

/**
 * 保单映射
 * 
 * @author ahab
 */
public class VehicleOrderMapper extends MongoMapper<String, VehicleOrder> {
	
	private String FIELD_BATCH_ID					= "batchId";
	private String FIELD_STATE						= "state";
	private String FIELD_CREATED					= "created";
	
	public VehicleOrderMapper() {
		super("vehicleOrder");
	}
	
	@Override
	public void insert(VehicleOrder model) {
		mongo.replaceOne(collection, Filters.eq(FIELD_ID, model.key()), serial(model), new UpdateOptions().upsert(true));
	}
	
	public void deleteBatchOrder(String batchId) {
		mongo.deleteMany(collection, Filters.eq(FIELD_BATCH_ID, batchId));
	}
	
	public List<VehicleOrder> list(EmployeeForm ef, VehicleOrderSearcher searcher) {
		List<VehicleOrder> orders = null;
		if (null != searcher.getBatchId())
			orders = mongo.find(collection, Filters.eq(FIELD_BATCH_ID, searcher.getBatchId()), VehicleOrder.class);
		else {
			List<Bson> list = new ArrayList<Bson>();
			if (null != searcher.getState())
				list.add(Filters.eq(FIELD_STATE, searcher.getState().toString()));
			long total = list.isEmpty() ? mongo.count(collection) : mongo.count(collection, Filters.and(list));
			if (0 == total)
				return Collections.EMPTY_LIST;
			searcher.calculate((int) total);
			orders = list.isEmpty() ?
					mongo.pagingAndSort(collection, Sorts.descending(FIELD_CREATED), 
								searcher.getStart(), searcher.getPageSize(), VehicleOrder.class):
					mongo.pagingAndSort(collection, Filters.and(list), Sorts.descending(FIELD_CREATED),
								searcher.getStart(), searcher.getPageSize(), VehicleOrder.class);
		}
		return orders;
	}
}
